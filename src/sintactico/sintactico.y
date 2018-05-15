%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.util.List;
import java.util.ArrayList;
import ast.*;
import ast.definicion.*;
import ast.expresion.*;
import ast.nodo.*;
import ast.tipo.*;
import ast.sentencia.*;
%}

// * Declaraciones Yacc
%token CTE_ENTERA
%token CTE_DECIMAL
%token LLA_AB
%token LLA_CE
%token PAR_AB
%token PAR_CE
%token COR_AB
%token COR_CE
%token CHAR
%token IDENT
%token OP_MAS
%token OP_MENOS
%token OP_MUL
%token OP_DIV
%token OP_MOD
%token SIM_COMIG
%token SIM_DIST
%token SIM_MAIG
%token SIM_MEIG
%token SIM_IG
%token SIM_CO
%token SIM_MA
%token SIM_PC
%token SIM_ME
%token BOOL_AND
%token BOOL_OR
%token BOOL_NOT
%token RES_READ
%token RES_WRITE
%token RES_WHILE
%token RES_IF
%token RES_ELSE
%token RES_INT
%token RES_F32
%token RES_CHAR
%token RES_VAR
%token RES_STRUCT
%token RES_RET
%token RES_FUNC
%token RES_MAIN
%token RES_VOID
%token SIM_PTO

%right SIM_IG
%left BOOL_AND BOOL_OR
%left SIM_COMIG SIM_MA SIM_ME SIM_DIST SIM_MAIG SIM_MEIG
%left OP_MAS OP_MENOS
%left OP_MUL OP_DIV OP_MOD
%right BOOL_NOT
%right MEN_IG
%nonassoc COR_AB COR_CE
%left SIM_PTO
%nonassoc PAR_AB PAR_CE
%nonassoc LLA_AB LLA_CE

%%
// * Gramática y acciones Yacc

programa: definiciones funcmain;

{
	List<Definicion> lista = (List<Definicion>) $1;
	lista.add((Definicion) $2);
	this.ast = new Programa(l(), c(), lista); 
}

funcmain: RES_FUNC RES_MAIN PAR_AB PAR_CE LLA_AB cuerpofuncion LLA_CE;		{
																				$$ = new DefFuncion(l(), c(), 
																				new TipoFuncion(l(), c(), TipoVoid.getInstancia(), new ArrayList<DefVariable>()), 
																				"main", (List<Sentencia>) $6);
																			}

definiciones: definiciones definicion {
										List<Definicion> lista = (List<Definicion>) $1;
										lista.addAll((List<Definicion>)$2);
										$$ = lista;
									  }
			| /* lambda */			  { $$ = new ArrayList<Definicion>(); }
			;

definicion: defvar			{ $$ = $1; }
		  | funcion			{
		  						List<Definicion> lista = new ArrayList<Definicion>();
		  						lista.add((Definicion) $1);
		  						$$ = lista;
		  					}
		  ;

defvaropc: defvaropc defvar		{
									List<DefVariable> lista = (List<DefVariable>)$1;
									lista.addAll((List<DefVariable>)$2);
									$$ = lista;
								}
		 | /* lambda */			{ $$ = new ArrayList<DefVariable>();}
		 ;

defvar: RES_VAR lvar tipo SIM_PC; 	{
										List<DefVariable> lista = new ArrayList<DefVariable>();
										for (int i=0; i<((List<Variable>)$2).size();i++) {
											lista.add(new DefVariable(l(), c(), ((List<Variable>)$2).get(i).getNombre(), (Tipo) $3));
										}
										$$ = lista;
									}

lvar: lvar SIM_CO IDENT		{
								List<Variable> lista = (List<Variable>) $1;
								Variable var = new Variable (l(), c(), (String) $3);
								if (lista.contains(var))
									new TipoError(l(), c(), "Nombre de variable duplicada");
								else
									lista.add(var);
								$$ = lista;
							}
	| IDENT					{
								List<Variable> lista = new ArrayList<Variable>();
								lista.add(new Variable(l(), c(), (String) $1));
								$$ = lista;
							}
	;

funcion: RES_FUNC IDENT PAR_AB parametrosopc PAR_CE tiposimple LLA_AB cuerpofuncion LLA_CE 	{
																							$$ = new DefFuncion(l(), c(), 
																							new TipoFuncion(l(), c(), (Tipo) $6, (List<DefVariable>) $4), 
																							(String) $2, (List<Sentencia>) $8);
																					 	}
	   | RES_FUNC IDENT PAR_AB parametrosopc PAR_CE LLA_AB cuerpofuncion LLA_CE 		{
																							$$ = new DefFuncion(l(), c(), 
																							new TipoFuncion(l(), c(), TipoVoid.getInstancia(), (List<DefVariable>) $4), 
																							(String) $2, (List<Sentencia>) $7);
																						}


cuerpofuncion: defvaropc sentenciasopc;		{
												List<Sentencia> lista = (List<Sentencia>) $1;
												lista.addAll((List<Sentencia>)$2);
												$$ = lista;
											}

parametrosopc: parametros		{ $$ = $1; }
			 | /* lambda */		{ $$ = new ArrayList<DefVariable>(); }
			 ;

parametros: parametros SIM_CO parametro		{ 
												List<DefVariable> lista = (List<DefVariable>) $1;
												lista.add((DefVariable) $3);
												$$ = lista;
											}
		  | parametro						{
						  						List<DefVariable> lista = new ArrayList<DefVariable>();
												lista.add((DefVariable) $1);
												$$ = lista;
						  					}
		  ;

parametro: IDENT tiposimple;		{ $$ = new DefVariable(l(), c(), (String)$1, (Tipo)$2); }

tipo: tiposimple							{ $$ = $1; }
	| COR_AB CTE_ENTERA COR_CE tipo			{ $$ = new TipoArray(l(), c(), (int)$2, (Tipo) $4); }
	| RES_STRUCT LLA_AB campos LLA_CE		{ $$ = new TipoStruct(l(), c(), (List<Campo>)$3); }
	;

tiposimple: RES_INT								{ $$ = TipoEntero.getInstancia(); }
		  | RES_F32								{ $$ = TipoDecimal.getInstancia(); }
	      | RES_CHAR							{ $$ = TipoChar.getInstancia(); }
	;
	
campos: campos campo		{
								List<Campo> lista = (List<Campo>)$1;
								List<Campo> templista = (List<Campo>)$2;
								for (Campo c: templista) 
									if (lista.contains(c))
										new TipoError(l(), c(), "El campo está duplicado");
									else
										lista.add(c);
								$$ = lista;
							}
	  | campo				{ $$ = $1; }
	  ;
	  
campo: lvar tipo SIM_PC;		{
									List<Variable> lista = (List<Variable>) $1;
									List<Campo> campos = new ArrayList<Campo>();
									for (int i=0; i<lista.size();i++) {
										campos.add(new Campo(l(), c(), lista.get(i).getNombre(), (Tipo) $2));
									}
									$$ = campos;
								}

		 
while: RES_WHILE PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE	{ $$ = new SentWhile(l(), c(), (Expresion)$3, (List<Sentencia>)$6); }
	 | RES_WHILE expresion LLA_AB sentencias LLA_CE					{ $$ = new SentWhile(l(), c(), (Expresion)$2, (List<Sentencia>)$4); }
	 ;
	 
if: RES_IF PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE else	{ $$ = new SentIf(l(), c(), (Expresion)$3, (List<Sentencia>)$6, (List<Sentencia>)$8); }
  | RES_IF expresion LLA_AB sentencias LLA_CE else					{ $$ = new SentIf(l(), c(), (Expresion)$2, (List<Sentencia>)$4, (List<Sentencia>)$6); }
  ;
  
else: RES_ELSE LLA_AB sentencias LLA_CE						{ $$ = $3; }
	| RES_ELSE PAR_AB PAR_CE LLA_AB sentencias LLA_CE		{ $$ = $5; }
	| /* lambda */											{ $$ = new ArrayList<Sentencia>(); }
	;
	
sentenciasopc: sentencias		{ $$ = $1; }
			 | /* lambda */		{ $$ = new ArrayList<Sentencia>(); }
			 ;

sentencias: sentencias sentencia		{
											List<Sentencia> lista = (List<Sentencia>) $1;
											lista.add((Sentencia) $2);
											$$ = lista;
										}
		  | sentencia					{
					  						List<Sentencia>lista = new ArrayList<Sentencia>();
					  						lista.add((Sentencia) $1);
					  						$$ = lista;
					  					}
		  ;

sentencia: RES_WRITE PAR_AB expresiones PAR_CE SIM_PC		{ $$ = new Escritura(l(), c(), (List<Expresion>) $3); }
		 | RES_READ PAR_AB expresiones PAR_CE SIM_PC		{ $$ = new Lectura(l(), c(), (List<Expresion>) $3); }
		 | expresion SIM_IG expresion SIM_PC				{ $$ = new Asignacion(l(), c(), (Expresion) $1, (Expresion) $3); }
		 | RES_RET expresion SIM_PC							{ $$ = new SentReturn(l(), c(), (Expresion) $2); }
		 | while											{ $$ = $1; }
		 | if												{ $$ = $1; }
		 | IDENT PAR_AB PAR_CE SIM_PC						{ $$ = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) $1), new ArrayList<Expresion>()); }
         | IDENT PAR_AB expresiones PAR_CE SIM_PC			{ $$ = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) $1), (List<Expresion>) $3); }
         ;

expresiones: expresiones SIM_CO expresion		{
													List<Expresion> lista = (List<Expresion>) $1;
													lista.add((Expresion) $3);
													$$ = lista;
												}
		   | expresion							{
		  											List<Expresion>lista = new ArrayList<Expresion>();
		  											lista.add((Expresion) $1);
		  											$$ = lista;
		  										}
		   ;

expresion: expresion OP_MAS expresion			{ $$ = new Aritmetica(l(), c(), (Expresion) $1, "+", (Expresion) $3); }
		 | expresion OP_MENOS expresion			{ $$ = new Aritmetica(l(), c(), (Expresion) $1, "-", (Expresion) $3); }
		 | expresion OP_MUL expresion			{ $$ = new Aritmetica(l(), c(), (Expresion) $1, "*", (Expresion) $3); }
		 | expresion OP_DIV expresion			{ $$ = new Aritmetica(l(), c(), (Expresion) $1, "/", (Expresion) $3); }
		 | expresion OP_MOD expresion			{ $$ = new Aritmetica(l(), c(), (Expresion) $1, "%", (Expresion) $3); }
		 | expresion BOOL_AND expresion			{ $$ = new LogicaBinaria(l(), c(), (Expresion) $1, "&&", (Expresion) $3); }
		 | expresion BOOL_OR expresion			{ $$ = new LogicaBinaria(l(), c(), (Expresion) $1, "||", (Expresion) $3); }
		 | BOOL_NOT expresion					{ $$ = new LogicaUnaria(l(), c(), (Expresion) $2); }
		 | expresion SIM_MA expresion			{ $$ = new Comparacion(l(), c(), (Expresion) $1, ">", (Expresion) $3); }
		 | expresion SIM_ME expresion			{ $$ = new Comparacion(l(), c(), (Expresion) $1, "<", (Expresion) $3); }
		 | expresion SIM_MAIG expresion			{ $$ = new Comparacion(l(), c(), (Expresion) $1, ">=", (Expresion) $3); }
		 | expresion SIM_MEIG expresion			{ $$ = new Comparacion(l(), c(), (Expresion) $1, "<=", (Expresion) $3); }
		 | expresion SIM_DIST expresion			{ $$ = new Comparacion(l(), c(), (Expresion) $1, "!=", (Expresion) $3); }
		 | expresion SIM_COMIG expresion		{ $$ = new Comparacion(l(), c(), (Expresion) $1, "==", (Expresion) $3); }
		 | OP_MENOS expresion %prec MEN_IG		{ $$ = new MenosUnario(l(), c(), (Expresion) $2); }
		 | PAR_AB expresion PAR_CE				{ $$ = $2; }
		 | IDENT								{ $$ = new Variable(l(), c(), (String) $1); }
         | CTE_ENTERA							{ $$ = new LiteralEntero(l(), c(), (int) $1); }
         | CTE_DECIMAL							{ $$ = new LiteralDecimal(l(), c(), (float) $1); }
         | CHAR									{ $$ = new LiteralChar(l(), c(), ((String) $1)); }
         | IDENT PAR_AB expresiones PAR_CE		{ $$ = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) $1), (List<Expresion>) $3); }
         | IDENT PAR_AB PAR_CE					{ $$ = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) $1), new ArrayList<Expresion>()); }
         | tipo PAR_AB expresion PAR_CE			{ $$ = new Cast(l(), c(), (Tipo)$1, (Expresion) $3); }
         | expresion COR_AB expresion COR_CE	{ $$ = new AccesoArray(l(), c(), (Expresion) $1, (Expresion) $3); }
         | expresion SIM_PTO IDENT				{ $$ = new AccesoCampo(l(), c(), (Expresion) $1, (String) $3); }
         ;
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;
private NodoAST ast;

private int l(){
	return lexico.getLinea();
}

private int c(){
	return lexico.getColumna();
}

public NodoAST getAST(){
	return ast;
}

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=lexico.yylex(); 	
		this.yylval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + l()+
		" y columna "+c()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + l()+
		" y columna "+c()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}
