// ************  CÛdigo a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * OpciÛn para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y mÈtodos ********************
// * Para acceder al n˙mero de lÌnea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n˙mero de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************
ReservadaRead = "read"
ReservadaWrite = "write"
ReservadaWhile = "while"
ReservadaIf = "if"
ReservadaElse = "else"
ReservadaInt = "int"
ReservadaFloat32 = "float32"
ReservadaChar = "char"
ReservadaVar = "var"
ReservadaStruct = "struct"
ReservadaReturn = "return"
ReservadaFunc = "func"
ReservadaMain = "main"
ReservadaVoid = "void"
ConstanteDecimalExp = [0-9]+"."[0-9]*[eE][+-]?[0-9]+ | [0-9]+[eE][+-]?[0-9]+
ConstanteEntera = [0-9]*
ConstanteDecimal = [0-9]+"."[0-9]*
SimboloPunto = "."
SimboloCompIg = "=="
SimboloDist = "!="
SimboloMayIg = ">="
SimboloMenIg = "<="
SimboloIgual = "="
SimboloPtoComa = ";"
SimboloComa = ","
SimboloMen = "<"
SimboloMay = ">"
OperadorMas = "+"
OperadorMenos = "-"
OperadorDiv = "/"
OperadorMul = "*"
OperadorMod = "%"
BoolAnd = "&&"
BoolOr = "||"
BoolNot = "!"
LlaveAbierta = "{"
LlaveCerrada = "}"
ParentesisAbierto = "("
ParentesisCerrado = ")"
CorcheteAbierto = "["
CorcheteCerrado = "]"
Char = '.' | '\\n' | '\\t' | '\\[0-9]+'
ComMulti = "/*"~"*/"
ComUni = "//".*
Blancos = [\ \n\r\t\f]
Identificador = [a-zA-Z_Ò—·ÈÌÛ˙¡…Õ”⁄][a-zA-Z0-9_Ò—·ÈÌÛ˙¡…Õ”⁄]*
SentAnd = "&&="
SentOr = "||="

%%
// ************  Acciones ********************
// * Reservada read
{ReservadaRead}	{this.yylval = yytext();
				 return Parser.RES_READ; }
				 
// * Reservada write
{ReservadaWrite}	{this.yylval = yytext();
				 return Parser.RES_WRITE; }
				 
// * Reservada while
{ReservadaWhile}	{this.yylval = yytext();
				 return Parser.RES_WHILE; }
				 
// * Reservada if
{ReservadaIf}	{this.yylval = yytext();
				 return Parser.RES_IF; }
				 
// * Reservada else
{ReservadaElse}	{this.yylval = yytext();
				 return Parser.RES_ELSE; }
				 
// * Reservada int
{ReservadaInt}	{this.yylval = yytext();
				 return Parser.RES_INT; }
				 
// * Reservada float32
{ReservadaFloat32}	{this.yylval = yytext();
				 return Parser.RES_F32; }
				 
// * Reservada char
{ReservadaChar}	{this.yylval = yytext();
				 return Parser.RES_CHAR; }
				 
// * Reservada var
{ReservadaVar}	{this.yylval = yytext();
				 return Parser.RES_VAR; }
				 
// * Reservada struct
{ReservadaStruct}	{this.yylval = yytext();
				 return Parser.RES_STRUCT; }
				
// * Reservada return
{ReservadaReturn}	{this.yylval = yytext();
				 return Parser.RES_RET; }
				 
// * Reservada func
{ReservadaFunc}	{this.yylval = yytext();
				 return Parser.RES_FUNC; }
				 
// * Reservada main
{ReservadaMain}	{this.yylval = yytext();
				 return Parser.RES_MAIN; }
				 
// * Reservada void
{ReservadaVoid}	{this.yylval = yytext();
				 return Parser.RES_VOID; }
				
// * Corchete abierto
{CorcheteAbierto}	{this.yylval = yytext();
				 return Parser.COR_AB; }
				 
// * Corchete cerrado
{CorcheteCerrado}	{this.yylval = yytext();
				 return Parser.COR_CE; }
				
// * Parentesis abierto
{ParentesisAbierto}	{this.yylval = yytext();
				 return Parser.PAR_AB; }
				 
// * Parentesis cerrado
{ParentesisCerrado}	{this.yylval = yytext();
				 return Parser.PAR_CE; }
				 
// * Llave abierta
{LlaveAbierta}	{this.yylval = yytext();
				 return Parser.LLA_AB; }
				 
// * Llave cerrada
{LlaveCerrada}	{this.yylval = yytext();
				 return Parser.LLA_CE; }
				 
// * Bool And
{BoolAnd}		{this.yylval = yytext();
				 return Parser.BOOL_AND; }
				 
// * Bool Or
{BoolOr}		{this.yylval = yytext();
				 return Parser.BOOL_OR; }
				 
// * Bool Not
{BoolNot}		{this.yylval = yytext();
				 return Parser.BOOL_NOT; }
				 
// * Operador mas
{OperadorMas}	{this.yylval = yytext();
				 return Parser.OP_MAS; }
			
// * Operador menos
{OperadorMenos}	{this.yylval = yytext();
				 return Parser.OP_MENOS; }
				 
// * Operador multiplicaciÛn
{OperadorMul}	{this.yylval = yytext();
				 return Parser.OP_MUL; }
				 
// * Operador mÛdulo
{OperadorMod}	{this.yylval = yytext();
				 return Parser.OP_MOD; }
				 
// * Operador divisiÛn
{OperadorDiv}	{this.yylval = yytext();
				 return Parser.OP_DIV; }
// * Simbolo Comparar igual
{SimboloCompIg}	{this.yylval = yytext();
				 return Parser.SIM_COMIG; }
				 
// * Simbolo Distinto
{SimboloDist}	{this.yylval = yytext();
				 return Parser.SIM_DIST; }
				 
// * Simbolo mayor igual
{SimboloMayIg}	{this.yylval = yytext();
				 return Parser.SIM_MAIG; }
				 
// * Simbolo menor igual
{SimboloMenIg}	{this.yylval = yytext();
				 return Parser.SIM_MEIG; }
				 			 
// * Simbolo igual
{SimboloIgual}	{this.yylval = yytext();
				 return Parser.SIM_IG; }
				 
// * Simbolo punto y coma
{SimboloPtoComa}	{this.yylval = yytext();
				 return Parser.SIM_PC; }
				 
// * Simbolo coma
{SimboloComa}	{this.yylval = yytext();
				 return Parser.SIM_CO; }
				 
// * Simbolo menor
{SimboloMen}	{this.yylval = yytext();
				 return Parser.SIM_ME; }
				 
// * Simbolo mayor
{SimboloMay}	{this.yylval = yytext();
				 return Parser.SIM_MA; }
				 
// * Char
{Char}	{this.yylval = yytext();
				 return Parser.CHAR; }

// * Constante Entera
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.CTE_ENTERA;  }

// * Constante Decimal
{ConstanteDecimalExp}  | {ConstanteDecimal} { this.yylval = new Float(yytext());
         			  return Parser.CTE_DECIMAL;  }			 

// * Identificador
{Identificador}	{ this.yylval = yytext();
				  return Parser.IDENT; }
				  
// * Simbolo punto
{SimboloPunto}	{this.yylval = yytext();
				 return Parser.SIM_PTO; }
				 
// * Sentencia and
{SentAnd}	{this.yylval = yytext();
				 return Parser.SEN_AND; }
				 
// * Sentencia or
{SentOr}	{this.yylval = yytext();
				 return Parser.SEN_OR; }
				  
// * Comentarios y blancos
{ComMulti} | {ComUni} | {Blancos}	{ }

// * Error
. { System.err.println("Error " + (this.yyline+1) + ":" + (this.yycolumn+1) + " Lexema = " + yytext());}