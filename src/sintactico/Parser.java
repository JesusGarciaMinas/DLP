//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "../../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.util.List;
import java.util.ArrayList;
import ast.*;
import ast.definicion.*;
import ast.expresion.*;
import ast.nodo.*;
import ast.tipo.*;
import ast.sentencia.*;
//#line 30 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_DECIMAL=258;
public final static short LLA_AB=259;
public final static short LLA_CE=260;
public final static short PAR_AB=261;
public final static short PAR_CE=262;
public final static short COR_AB=263;
public final static short COR_CE=264;
public final static short CHAR=265;
public final static short IDENT=266;
public final static short OP_MAS=267;
public final static short OP_MENOS=268;
public final static short OP_MUL=269;
public final static short OP_DIV=270;
public final static short OP_MOD=271;
public final static short SIM_COMIG=272;
public final static short SIM_DIST=273;
public final static short SIM_MAIG=274;
public final static short SIM_MEIG=275;
public final static short SIM_IG=276;
public final static short SIM_CO=277;
public final static short SIM_MA=278;
public final static short SIM_PC=279;
public final static short SIM_ME=280;
public final static short BOOL_AND=281;
public final static short BOOL_OR=282;
public final static short BOOL_NOT=283;
public final static short RES_READ=284;
public final static short RES_WRITE=285;
public final static short RES_WHILE=286;
public final static short RES_IF=287;
public final static short RES_ELSE=288;
public final static short RES_INT=289;
public final static short RES_F32=290;
public final static short RES_CHAR=291;
public final static short RES_VAR=292;
public final static short RES_STRUCT=293;
public final static short RES_RET=294;
public final static short RES_FUNC=295;
public final static short RES_MAIN=296;
public final static short RES_VOID=297;
public final static short SIM_PTO=298;
public final static short SEN_AND=299;
public final static short SEN_OR=300;
public final static short MEN_IG=301;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    1,    1,    4,    4,    7,    7,    5,    8,
    8,    6,    6,    3,   10,   10,   13,   13,   14,    9,
    9,    9,   11,   11,   11,   15,   15,   16,   17,   17,
   20,   20,   21,   21,   21,   12,   12,   19,   19,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   23,
   23,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,    7,    2,    0,    1,    1,    2,    0,    4,    3,
    1,    9,    8,    2,    1,    0,    3,    1,    2,    1,
    4,    4,    1,    1,    1,    2,    1,    3,    7,    5,
    8,    6,    4,    6,    0,    1,    0,    2,    1,    5,
    5,    4,    3,    1,    1,    4,    5,    4,    4,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    2,    3,
    3,    3,    3,    3,    3,    2,    3,    1,    1,    1,
    1,    4,    3,    4,    4,    3,    3,    3,
};
final static short yydefred[] = {                         4,
    0,    0,    0,    0,    1,    3,    5,    6,   11,    0,
    0,    0,    0,    0,   23,   24,   25,    0,    0,   20,
    0,    0,    0,   10,    0,    9,    0,    0,    0,   18,
    0,    0,    0,    0,   27,   19,    0,    0,    8,   21,
    0,   22,   26,    8,    0,   17,    0,    0,   28,    0,
    8,    2,   69,   70,    0,   71,    0,    0,    0,    0,
    0,    0,    0,    0,    7,    0,   14,   44,    0,    0,
   45,   39,   13,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   38,   12,    0,   67,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   43,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   76,    0,    0,
   73,    0,    0,    0,   46,    0,    0,    0,    0,    0,
    0,    0,    0,   74,   75,   42,   48,   49,   72,   47,
    0,   41,   40,    0,   30,    0,    0,    0,    0,    0,
   32,   29,    0,    0,    0,   31,    0,    0,   33,    0,
    0,   34,
};
final static short yydgoto[] = {                          1,
    2,    5,   47,    6,    7,    8,   48,   33,   66,   28,
   20,   67,   29,   30,   34,   35,   68,   69,   70,   71,
  171,   72,  114,
};
final static short yysindex[] = {                         0,
    0, -198, -255, -264,    0,    0,    0,    0,    0,   57,
 -254, -237, -242, -213,    0,    0,    0, -193, -218,    0,
 -195, -190, -172,    0, -255,    0, -116, -167, -181,    0,
 -159, -151,   57, -241,    0,    0, -200, -195,    0,    0,
 -178,    0,    0,    0, -153,    0, -131,  475,    0, -130,
    0,    0,    0,    0,  902,    0, -129,  902,  902, -126,
 -124, 1063, 1113,  902,    0, -120,    0,    0,  507, 1006,
    0,    0,    0, -127, -118,  347,  535, -246, -246,  902,
  902,  902,   58,  902,   92,  541,  902,  902,  902,  902,
  902,  902,  902,  902,  902,  902,  902,  902,  902,  902,
  902,  902,  -90,  902,  902,    0,    0,  607,    0,  902,
  902, -102, 1035, -259, -207, -199,  381, 1006,  415, 1006,
    0,  449,  579, -194, -194, -246, -246, -246,  -58,  -58,
  -58,  -58,  613,  -58,  -58, 1117, 1117,    0,  647,  681,
    0, -189, 1035, 1035,    0, -101,  902, -100,  -99,  -77,
  707,  -76,  745,    0,    0,    0,    0,    0,    0,    0,
 1035,    0,    0, 1006,    0, 1006, -103,  757,  795, -230,
    0,    0, -103, 1006,  -56,    0,  807,  -52,    0, 1006,
  845,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -48,    0,    0,    0,    0,    0,    0,    0,  -46,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -43,    0,    0,
    0,    0,    0,    0,    0,    0,  874,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -41,
    0,    0,    0,    0, -154,    0,    0, -112,  -78,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  908,   45,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  121,  150,  -44,  -10,   24,  179,  208,
  237,  266,    0,  295,  324, -197, -128,    0,  -68,  -37,
    0,    0, 1096, 1146,    0,  942,    0,    0,    0, 1069,
    0, 1069,    0,    0,    0,    0,    0,    0,    0,    0,
   75,    0,    0,    0,    0,    0,  968,    0,    0,    0,
    0,    0,  968,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -31,    0,  193,    0,    0,  239,   54,    0,
  -21,    0,    0,  205,    0,  210,    0,  -54, -106,    0,
   72,  -70,  -59,
};
final static int YYTABLESIZE=1425;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        106,
   76,   11,  146,   78,   79,   36,   21,   83,   85,   86,
    9,  151,   50,  153,   23,   45,   88,  147,   42,   74,
  115,  116,  113,   22,    9,  113,  113,  117,  174,  119,
  175,   12,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,  136,  137,  142,  139,
  140,  103,   24,  113,  148,  143,  144,  168,   44,  169,
   26,   57,  149,   19,   57,   25,   57,  177,   88,  147,
   27,   31,  159,  181,   91,   92,   93,  147,   57,   57,
  106,   57,  106,   57,   57,   40,   41,  147,   15,   16,
   17,   32,  161,    3,   37,   38,    4,  106,  106,   39,
   49,   57,   57,  103,   68,   51,  106,   68,   68,   68,
  106,   13,   68,   68,   68,   68,   68,   68,   68,   68,
   68,   68,   68,   68,   68,   68,   68,   68,   52,   73,
   58,   77,  107,   58,   80,   58,   81,   15,   16,   17,
   87,   18,  108,   68,   68,   68,   66,   58,   58,   66,
   58,   66,   58,   58,   66,   66,   66,   66,   66,   66,
   66,   66,   66,   66,   66,   66,   66,   66,   66,   66,
   58,   58,   15,   16,   17,  138,  145,  160,  162,  163,
   59,  164,  166,   59,  170,   59,   66,   66,   59,   59,
   59,   59,   59,   59,   59,   59,   59,   59,   59,   59,
   59,   59,   59,   59,   88,  178,  180,   77,   89,   90,
   91,   92,   93,   16,   54,   15,   37,   54,   36,   54,
   59,   59,   54,   54,   54,   54,   54,   54,   54,   54,
   54,   54,   54,   54,   54,   54,   54,   54,   78,  103,
   65,   10,   46,   43,  176,    0,    0,    0,   55,    0,
    0,   55,    0,   55,   54,   54,   55,   55,   55,   55,
   55,   55,   55,   55,   55,   55,   55,   55,   55,   55,
   55,   55,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,    0,    0,   56,    0,   56,   55,   55,
   56,   56,   56,   56,   56,   56,   56,   56,   56,   56,
   56,   56,   56,   56,   56,   56,   51,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  118,    0,    0,   13,
   88,   51,   56,   56,   89,   90,   91,   92,   93,   94,
   95,   96,   97,   14,    0,   99,   50,  100,  101,  102,
    0,    0,    0,    0,    0,   15,   16,   17,    0,   18,
  120,   50,    0,    0,   88,  103,  110,  111,   89,   90,
   91,   92,   93,   94,   95,   96,   97,    0,    0,   99,
    0,  100,  101,  102,    0,    0,    0,    0,    0,   52,
    0,    0,   52,    0,   52,    0,    0,   52,   52,  103,
  110,  111,   52,   52,   52,   52,   52,   52,   52,   52,
   52,   52,   52,    0,    0,    0,    0,    0,   53,    0,
    0,   53,    0,   53,    0,    0,   53,   53,    0,   52,
   52,   53,   53,   53,   53,   53,   53,   53,   53,   53,
   53,   53,    0,    0,    0,    0,    0,   65,    0,    0,
   65,    0,   65,    0,    0,    0,    0,    0,   53,   53,
   65,   65,   65,   65,   65,   65,   65,   65,   65,   65,
   65,    0,    0,    0,    0,    0,   64,    0,    0,   64,
    0,   64,    0,    0,    0,    0,    0,   65,   65,   64,
   64,   64,   64,   64,   64,   64,   64,   64,   64,   64,
    0,    0,    0,    0,    0,   62,    0,    0,   62,    0,
   62,    0,    0,    0,    0,    0,   64,   64,   62,   62,
   62,   62,   62,   62,   62,   62,   62,   62,   62,    0,
    0,    0,    0,    0,   63,    0,    0,   63,    0,   63,
    0,    0,    0,    0,    0,   62,   62,   63,   63,   63,
   63,   63,   63,   63,   63,   63,   63,   63,    0,    0,
    0,    0,    0,   60,    0,    0,   60,    0,   60,    0,
    0,    0,    0,    0,   63,   63,   60,   60,   60,   60,
   60,   60,   60,   60,   60,   60,   60,    0,    0,    0,
    0,    0,   61,    0,    0,   61,    0,   61,    0,    0,
    0,    0,    0,   60,   60,   61,   61,   61,   61,   61,
   61,   61,   61,   61,   61,   61,    0,    0,  109,   88,
    0,    0,    0,   89,   90,   91,   92,   93,   94,   95,
   96,   97,   61,   61,   99,    0,  100,  101,  102,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  150,   88,  103,  110,  111,   89,   90,   91,
   92,   93,   94,   95,   96,   97,    0,    0,   99,    0,
  100,  101,  102,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  152,   88,  103,  110,
  111,   89,   90,   91,   92,   93,   94,   95,   96,   97,
    0,    0,   99,    0,  100,  101,  102,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  154,   88,  103,  110,  111,   89,   90,   91,   92,   93,
   94,   95,   96,   97,    0,    0,   99,    0,  100,  101,
  102,   53,   54,    0,    0,   55,    0,   13,    0,   56,
   57,    0,   58,    0,    0,    0,  103,  110,  111,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   60,   61,
   62,   63,    0,   15,   16,   17,    3,   18,   64,   88,
    0,    0,    0,   89,   90,   91,   92,   93,   94,   95,
   96,   97,   98,    0,   99,    0,  100,  101,  102,    0,
    0,   53,   54,    0,    0,   55,  112,   13,    0,   56,
   75,    0,   58,   88,  103,  104,  105,   89,   90,   91,
   92,   93,   94,   95,   96,   97,    0,   59,   99,  121,
  100,  101,  102,   15,   16,   17,    0,   18,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  103,  110,
  111,   88,  155,    0,    0,   89,   90,   91,   92,   93,
   94,   95,   96,   97,    0,    0,   99,    0,  100,  101,
  102,    0,    0,   53,   54,    0,    0,   55,  141,   13,
    0,   56,   75,    0,   58,   88,  103,  110,  111,   89,
   90,   91,   92,   93,   94,   95,   96,   97,    0,   59,
   99,  156,  100,  101,  102,   15,   16,   17,    0,   18,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   88,
  103,  110,  111,   89,   90,   91,   92,   93,   94,   95,
   96,   97,    0,    0,   99,  157,  100,  101,  102,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   88,  103,  110,  111,   89,   90,   91,
   92,   93,   94,   95,   96,   97,    0,    0,   99,  158,
  100,  101,  102,   53,   54,    0,  165,   55,    0,   13,
    0,   56,   57,    0,   58,    0,    0,    0,  103,  110,
  111,    0,    0,    0,    0,    0,    0,    0,    0,   59,
   60,   61,   62,   63,    0,   15,   16,   17,    0,   18,
   64,   53,   54,    0,  167,   55,    0,   13,    0,   56,
   57,    0,   58,   53,   54,    0,  172,   55,    0,   13,
    0,   56,   57,    0,   58,    0,    0,   59,   60,   61,
   62,   63,    0,   15,   16,   17,    0,   18,   64,   59,
   60,   61,   62,   63,    0,   15,   16,   17,    0,   18,
   64,   53,   54,    0,  173,   55,    0,   13,    0,   56,
   57,    0,   58,   53,   54,    0,  179,   55,    0,   13,
    0,   56,   57,    0,   58,    0,    0,   59,   60,   61,
   62,   63,    0,   15,   16,   17,    0,   18,   64,   59,
   60,   61,   62,   63,    0,   15,   16,   17,    0,   18,
   64,   53,   54,    0,  182,   55,    0,   13,    0,   56,
   57,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   60,   61,
   62,   63,    0,   15,   16,   17,   68,   18,   64,    0,
   68,   68,   68,   68,   68,   68,   68,   68,   68,   68,
    0,   68,    0,   68,   68,   68,    0,    0,   53,   54,
    0,    0,   55,    0,   13,    0,   56,   75,    0,   58,
   73,   68,   68,   68,   73,   73,   73,   73,   73,   73,
   73,   73,   73,   73,   59,   73,    0,   73,   73,   73,
   15,   16,   17,    0,   18,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   72,   73,   73,   73,   72,   72,
   72,   72,   72,   72,   72,   72,   72,   72,    0,   72,
    0,   72,   72,   72,   35,   35,    0,   35,   35,    0,
   35,    0,   35,   35,    0,   35,    0,    0,    0,   72,
   72,   72,    0,    0,    0,    0,    0,    0,    0,    0,
   35,   35,   35,   35,   35,    0,   35,   35,   35,    0,
   35,   35,   53,   54,    0,    0,   55,    0,   13,    0,
   56,   57,    0,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   59,   60,
   61,   62,   63,    0,   15,   16,   17,   88,   18,   64,
    0,   89,   90,   91,   92,   93,   94,   95,   96,   97,
    0,    0,   99,    0,  100,  101,  102,    0,    0,   53,
   54,    0,    0,   82,    0,   13,    0,   56,   75,    0,
   58,   67,  103,  110,  111,   67,   67,   67,   67,   67,
   67,   67,   67,   67,    0,   59,   67,    0,   67,   67,
   67,   15,   16,   17,   77,   18,    0,   77,    0,   77,
    0,    0,    0,    0,    0,    0,   67,   67,   67,   53,
   54,   77,   77,   84,   77,   13,    0,   56,   75,   88,
   58,    0,    0,   89,   90,   91,   92,   93,   94,   95,
   96,   97,    0,    0,   99,   59,  100,    0,    0,    0,
    0,   15,   16,   17,   78,   18,    0,   78,    0,   78,
    0,    0,    0,    0,  103,    0,    0,    0,    0,    0,
    0,   78,   78,    0,   78,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         70,
   55,  266,  262,   58,   59,   27,  261,   62,   63,   64,
  266,  118,   44,  120,  257,   37,  263,  277,  260,   51,
   80,   81,   77,  261,  266,   80,   81,   82,  259,   84,
  261,  296,   87,   88,   89,   90,   91,   92,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,  108,  104,
  105,  298,  266,  108,  262,  110,  111,  164,  259,  166,
  279,  259,  262,   10,  262,  259,  264,  174,  263,  277,
  266,  262,  262,  180,  269,  270,  271,  277,  276,  277,
  151,  279,  153,  281,  282,   32,   33,  277,  289,  290,
  291,  264,  147,  292,  262,  277,  295,  168,  169,  259,
  279,  299,  300,  298,  259,  259,  177,  262,  263,  264,
  181,  263,  267,  268,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,  260,  260,
  259,  261,  260,  262,  261,  264,  261,  289,  290,  291,
  261,  293,  261,  298,  299,  300,  259,  276,  277,  262,
  279,  264,  281,  282,  267,  268,  269,  270,  271,  272,
  273,  274,  275,  276,  277,  278,  279,  280,  281,  282,
  299,  300,  289,  290,  291,  266,  279,  279,  279,  279,
  259,  259,  259,  262,  288,  264,  299,  300,  267,  268,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  278,
  279,  280,  281,  282,  263,  262,  259,  276,  267,  268,
  269,  270,  271,  262,  259,  262,  260,  262,  260,  264,
  299,  300,  267,  268,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,  276,  298,
   48,    3,   38,   34,  173,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,  264,  299,  300,  267,  268,  269,  270,
  271,  272,  273,  274,  275,  276,  277,  278,  279,  280,
  281,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,  264,  299,  300,
  267,  268,  269,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  281,  282,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  263,
  263,  277,  299,  300,  267,  268,  269,  270,  271,  272,
  273,  274,  275,  277,   -1,  278,  262,  280,  281,  282,
   -1,   -1,   -1,   -1,   -1,  289,  290,  291,   -1,  293,
  259,  277,   -1,   -1,  263,  298,  299,  300,  267,  268,
  269,  270,  271,  272,  273,  274,  275,   -1,   -1,  278,
   -1,  280,  281,  282,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,  262,   -1,  264,   -1,   -1,  267,  268,  298,
  299,  300,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,  282,   -1,   -1,   -1,   -1,   -1,  259,   -1,
   -1,  262,   -1,  264,   -1,   -1,  267,  268,   -1,  299,
  300,  272,  273,  274,  275,  276,  277,  278,  279,  280,
  281,  282,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,
  262,   -1,  264,   -1,   -1,   -1,   -1,   -1,  299,  300,
  272,  273,  274,  275,  276,  277,  278,  279,  280,  281,
  282,   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,
   -1,  264,   -1,   -1,   -1,   -1,   -1,  299,  300,  272,
  273,  274,  275,  276,  277,  278,  279,  280,  281,  282,
   -1,   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,
  264,   -1,   -1,   -1,   -1,   -1,  299,  300,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,   -1,
   -1,   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,  264,
   -1,   -1,   -1,   -1,   -1,  299,  300,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,   -1,   -1,  259,   -1,   -1,  262,   -1,  264,   -1,
   -1,   -1,   -1,   -1,  299,  300,  272,  273,  274,  275,
  276,  277,  278,  279,  280,  281,  282,   -1,   -1,   -1,
   -1,   -1,  259,   -1,   -1,  262,   -1,  264,   -1,   -1,
   -1,   -1,   -1,  299,  300,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  281,  282,   -1,   -1,  262,  263,
   -1,   -1,   -1,  267,  268,  269,  270,  271,  272,  273,
  274,  275,  299,  300,  278,   -1,  280,  281,  282,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  262,  263,  298,  299,  300,  267,  268,  269,
  270,  271,  272,  273,  274,  275,   -1,   -1,  278,   -1,
  280,  281,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  262,  263,  298,  299,
  300,  267,  268,  269,  270,  271,  272,  273,  274,  275,
   -1,   -1,  278,   -1,  280,  281,  282,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  262,  263,  298,  299,  300,  267,  268,  269,  270,  271,
  272,  273,  274,  275,   -1,   -1,  278,   -1,  280,  281,
  282,  257,  258,   -1,   -1,  261,   -1,  263,   -1,  265,
  266,   -1,  268,   -1,   -1,   -1,  298,  299,  300,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,   -1,  289,  290,  291,  292,  293,  294,  263,
   -1,   -1,   -1,  267,  268,  269,  270,  271,  272,  273,
  274,  275,  276,   -1,  278,   -1,  280,  281,  282,   -1,
   -1,  257,  258,   -1,   -1,  261,  262,  263,   -1,  265,
  266,   -1,  268,  263,  298,  299,  300,  267,  268,  269,
  270,  271,  272,  273,  274,  275,   -1,  283,  278,  279,
  280,  281,  282,  289,  290,  291,   -1,  293,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  298,  299,
  300,  263,  264,   -1,   -1,  267,  268,  269,  270,  271,
  272,  273,  274,  275,   -1,   -1,  278,   -1,  280,  281,
  282,   -1,   -1,  257,  258,   -1,   -1,  261,  262,  263,
   -1,  265,  266,   -1,  268,  263,  298,  299,  300,  267,
  268,  269,  270,  271,  272,  273,  274,  275,   -1,  283,
  278,  279,  280,  281,  282,  289,  290,  291,   -1,  293,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  263,
  298,  299,  300,  267,  268,  269,  270,  271,  272,  273,
  274,  275,   -1,   -1,  278,  279,  280,  281,  282,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  263,  298,  299,  300,  267,  268,  269,
  270,  271,  272,  273,  274,  275,   -1,   -1,  278,  279,
  280,  281,  282,  257,  258,   -1,  260,  261,   -1,  263,
   -1,  265,  266,   -1,  268,   -1,   -1,   -1,  298,  299,
  300,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,
  284,  285,  286,  287,   -1,  289,  290,  291,   -1,  293,
  294,  257,  258,   -1,  260,  261,   -1,  263,   -1,  265,
  266,   -1,  268,  257,  258,   -1,  260,  261,   -1,  263,
   -1,  265,  266,   -1,  268,   -1,   -1,  283,  284,  285,
  286,  287,   -1,  289,  290,  291,   -1,  293,  294,  283,
  284,  285,  286,  287,   -1,  289,  290,  291,   -1,  293,
  294,  257,  258,   -1,  260,  261,   -1,  263,   -1,  265,
  266,   -1,  268,  257,  258,   -1,  260,  261,   -1,  263,
   -1,  265,  266,   -1,  268,   -1,   -1,  283,  284,  285,
  286,  287,   -1,  289,  290,  291,   -1,  293,  294,  283,
  284,  285,  286,  287,   -1,  289,  290,  291,   -1,  293,
  294,  257,  258,   -1,  260,  261,   -1,  263,   -1,  265,
  266,   -1,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,   -1,  289,  290,  291,  263,  293,  294,   -1,
  267,  268,  269,  270,  271,  272,  273,  274,  275,  276,
   -1,  278,   -1,  280,  281,  282,   -1,   -1,  257,  258,
   -1,   -1,  261,   -1,  263,   -1,  265,  266,   -1,  268,
  263,  298,  299,  300,  267,  268,  269,  270,  271,  272,
  273,  274,  275,  276,  283,  278,   -1,  280,  281,  282,
  289,  290,  291,   -1,  293,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  263,  298,  299,  300,  267,  268,
  269,  270,  271,  272,  273,  274,  275,  276,   -1,  278,
   -1,  280,  281,  282,  257,  258,   -1,  260,  261,   -1,
  263,   -1,  265,  266,   -1,  268,   -1,   -1,   -1,  298,
  299,  300,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  283,  284,  285,  286,  287,   -1,  289,  290,  291,   -1,
  293,  294,  257,  258,   -1,   -1,  261,   -1,  263,   -1,
  265,  266,   -1,  268,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,
  285,  286,  287,   -1,  289,  290,  291,  263,  293,  294,
   -1,  267,  268,  269,  270,  271,  272,  273,  274,  275,
   -1,   -1,  278,   -1,  280,  281,  282,   -1,   -1,  257,
  258,   -1,   -1,  261,   -1,  263,   -1,  265,  266,   -1,
  268,  263,  298,  299,  300,  267,  268,  269,  270,  271,
  272,  273,  274,  275,   -1,  283,  278,   -1,  280,  281,
  282,  289,  290,  291,  259,  293,   -1,  262,   -1,  264,
   -1,   -1,   -1,   -1,   -1,   -1,  298,  299,  300,  257,
  258,  276,  277,  261,  279,  263,   -1,  265,  266,  263,
  268,   -1,   -1,  267,  268,  269,  270,  271,  272,  273,
  274,  275,   -1,   -1,  278,  283,  280,   -1,   -1,   -1,
   -1,  289,  290,  291,  259,  293,   -1,  262,   -1,  264,
   -1,   -1,   -1,   -1,  298,   -1,   -1,   -1,   -1,   -1,
   -1,  276,  277,   -1,  279,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=301;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"CTE_ENTERA","CTE_DECIMAL","LLA_AB","LLA_CE","PAR_AB","PAR_CE",
"COR_AB","COR_CE","CHAR","IDENT","OP_MAS","OP_MENOS","OP_MUL","OP_DIV","OP_MOD",
"SIM_COMIG","SIM_DIST","SIM_MAIG","SIM_MEIG","SIM_IG","SIM_CO","SIM_MA",
"SIM_PC","SIM_ME","BOOL_AND","BOOL_OR","BOOL_NOT","RES_READ","RES_WRITE",
"RES_WHILE","RES_IF","RES_ELSE","RES_INT","RES_F32","RES_CHAR","RES_VAR",
"RES_STRUCT","RES_RET","RES_FUNC","RES_MAIN","RES_VOID","SIM_PTO","SEN_AND",
"SEN_OR","MEN_IG",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones funcmain",
"funcmain : RES_FUNC RES_MAIN PAR_AB PAR_CE LLA_AB cuerpofuncion LLA_CE",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : defvar",
"definicion : funcion",
"defvaropc : defvaropc defvar",
"defvaropc :",
"defvar : RES_VAR lvar tipo SIM_PC",
"lvar : lvar SIM_CO IDENT",
"lvar : IDENT",
"funcion : RES_FUNC IDENT PAR_AB parametrosopc PAR_CE tiposimple LLA_AB cuerpofuncion LLA_CE",
"funcion : RES_FUNC IDENT PAR_AB parametrosopc PAR_CE LLA_AB cuerpofuncion LLA_CE",
"cuerpofuncion : defvaropc sentenciasopc",
"parametrosopc : parametros",
"parametrosopc :",
"parametros : parametros SIM_CO parametro",
"parametros : parametro",
"parametro : IDENT tiposimple",
"tipo : tiposimple",
"tipo : COR_AB CTE_ENTERA COR_CE tipo",
"tipo : RES_STRUCT LLA_AB campos LLA_CE",
"tiposimple : RES_INT",
"tiposimple : RES_F32",
"tiposimple : RES_CHAR",
"campos : campos campo",
"campos : campo",
"campo : lvar tipo SIM_PC",
"while : RES_WHILE PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE",
"while : RES_WHILE expresion LLA_AB sentencias LLA_CE",
"if : RES_IF PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE else",
"if : RES_IF expresion LLA_AB sentencias LLA_CE else",
"else : RES_ELSE LLA_AB sentencias LLA_CE",
"else : RES_ELSE PAR_AB PAR_CE LLA_AB sentencias LLA_CE",
"else :",
"sentenciasopc : sentencias",
"sentenciasopc :",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : RES_WRITE PAR_AB expresiones PAR_CE SIM_PC",
"sentencia : RES_READ PAR_AB expresiones PAR_CE SIM_PC",
"sentencia : expresion SIM_IG expresion SIM_PC",
"sentencia : RES_RET expresion SIM_PC",
"sentencia : while",
"sentencia : if",
"sentencia : IDENT PAR_AB PAR_CE SIM_PC",
"sentencia : IDENT PAR_AB expresiones PAR_CE SIM_PC",
"sentencia : expresion SEN_AND expresion SIM_PC",
"sentencia : expresion SEN_OR expresion SIM_PC",
"expresiones : expresiones SIM_CO expresion",
"expresiones : expresion",
"expresion : expresion OP_MAS expresion",
"expresion : expresion OP_MENOS expresion",
"expresion : expresion OP_MUL expresion",
"expresion : expresion OP_DIV expresion",
"expresion : expresion OP_MOD expresion",
"expresion : expresion BOOL_AND expresion",
"expresion : expresion BOOL_OR expresion",
"expresion : BOOL_NOT expresion",
"expresion : expresion SIM_MA expresion",
"expresion : expresion SIM_ME expresion",
"expresion : expresion SIM_MAIG expresion",
"expresion : expresion SIM_MEIG expresion",
"expresion : expresion SIM_DIST expresion",
"expresion : expresion SIM_COMIG expresion",
"expresion : OP_MENOS expresion",
"expresion : PAR_AB expresion PAR_CE",
"expresion : IDENT",
"expresion : CTE_ENTERA",
"expresion : CTE_DECIMAL",
"expresion : CHAR",
"expresion : IDENT PAR_AB expresiones PAR_CE",
"expresion : IDENT PAR_AB PAR_CE",
"expresion : tipo PAR_AB expresion PAR_CE",
"expresion : expresion COR_AB expresion COR_CE",
"expresion : expresion SIM_PTO IDENT",
"expresion : expresion SEN_AND expresion",
"expresion : expresion SEN_OR expresion",
};

//#line 291 "../../src/sintactico/sintactico.y"

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
//#line 697 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 79 "../../src/sintactico/sintactico.y"
{
	List<Definicion> lista = (List<Definicion>) val_peek(1);
	lista.add((Definicion) val_peek(0));
	this.ast = new Programa(l(), c(), lista); 
}
break;
case 2:
//#line 85 "../../src/sintactico/sintactico.y"
{
																				yyval = new DefFuncion(l(), c(), 
																				new TipoFuncion(l(), c(), TipoVoid.getInstancia(), new ArrayList<DefVariable>()), 
																				"main", (List<Sentencia>) val_peek(1));
																			}
break;
case 3:
//#line 91 "../../src/sintactico/sintactico.y"
{
										List<Definicion> lista = (List<Definicion>) val_peek(1);
										lista.addAll((List<Definicion>)val_peek(0));
										yyval = lista;
									  }
break;
case 4:
//#line 96 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 5:
//#line 99 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 100 "../../src/sintactico/sintactico.y"
{
		  						List<Definicion> lista = new ArrayList<Definicion>();
		  						lista.add((Definicion) val_peek(0));
		  						yyval = lista;
		  					}
break;
case 7:
//#line 107 "../../src/sintactico/sintactico.y"
{
									List<DefVariable> lista = (List<DefVariable>)val_peek(1);
									lista.addAll((List<DefVariable>)val_peek(0));
									yyval = lista;
								}
break;
case 8:
//#line 112 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 115 "../../src/sintactico/sintactico.y"
{
										List<DefVariable> lista = new ArrayList<DefVariable>();
										for (int i=0; i<((List<Variable>)val_peek(2)).size();i++) {
											lista.add(new DefVariable(l(), c(), ((List<Variable>)val_peek(2)).get(i).getNombre(), (Tipo) val_peek(1)));
										}
										yyval = lista;
									}
break;
case 10:
//#line 123 "../../src/sintactico/sintactico.y"
{
								List<Variable> lista = (List<Variable>) val_peek(2);
								Variable var = new Variable (l(), c(), (String) val_peek(0));
								if (lista.contains(var))
									new TipoError(l(), c(), "Nombre de variable duplicada");
								else
									lista.add(var);
								yyval = lista;
							}
break;
case 11:
//#line 132 "../../src/sintactico/sintactico.y"
{
								List<Variable> lista = new ArrayList<Variable>();
								lista.add(new Variable(l(), c(), (String) val_peek(0)));
								yyval = lista;
							}
break;
case 12:
//#line 139 "../../src/sintactico/sintactico.y"
{
																							yyval = new DefFuncion(l(), c(), 
																							new TipoFuncion(l(), c(), (Tipo) val_peek(3), (List<DefVariable>) val_peek(5)), 
																							(String) val_peek(7), (List<Sentencia>) val_peek(1));
																					 	}
break;
case 13:
//#line 144 "../../src/sintactico/sintactico.y"
{
																							yyval = new DefFuncion(l(), c(), 
																							new TipoFuncion(l(), c(), TipoVoid.getInstancia(), (List<DefVariable>) val_peek(4)), 
																							(String) val_peek(6), (List<Sentencia>) val_peek(1));
																						}
break;
case 14:
//#line 152 "../../src/sintactico/sintactico.y"
{
												List<Sentencia> lista = (List<Sentencia>) val_peek(1);
												lista.addAll((List<Sentencia>)val_peek(0));
												yyval = lista;
											}
break;
case 15:
//#line 158 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 16:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); }
break;
case 17:
//#line 162 "../../src/sintactico/sintactico.y"
{ 
												List<DefVariable> lista = (List<DefVariable>) val_peek(2);
												lista.add((DefVariable) val_peek(0));
												yyval = lista;
											}
break;
case 18:
//#line 167 "../../src/sintactico/sintactico.y"
{
						  						List<DefVariable> lista = new ArrayList<DefVariable>();
												lista.add((DefVariable) val_peek(0));
												yyval = lista;
						  					}
break;
case 19:
//#line 174 "../../src/sintactico/sintactico.y"
{ yyval = new DefVariable(l(), c(), (String)val_peek(1), (Tipo)val_peek(0)); }
break;
case 20:
//#line 176 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 21:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = new TipoArray(l(), c(), (int)val_peek(2), (Tipo) val_peek(0)); }
break;
case 22:
//#line 178 "../../src/sintactico/sintactico.y"
{ yyval = new TipoStruct(l(), c(), (List<Campo>)val_peek(1)); }
break;
case 23:
//#line 181 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstancia(); }
break;
case 24:
//#line 182 "../../src/sintactico/sintactico.y"
{ yyval = TipoDecimal.getInstancia(); }
break;
case 25:
//#line 183 "../../src/sintactico/sintactico.y"
{ yyval = TipoChar.getInstancia(); }
break;
case 26:
//#line 186 "../../src/sintactico/sintactico.y"
{
								List<Campo> lista = (List<Campo>)val_peek(1);
								List<Campo> templista = (List<Campo>)val_peek(0);
								for (Campo c: templista) 
									if (lista.contains(c))
										new TipoError(l(), c(), "El campo está duplicado");
									else
										lista.add(c);
								yyval = lista;
							}
break;
case 27:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 28:
//#line 199 "../../src/sintactico/sintactico.y"
{
									List<Variable> lista = (List<Variable>) val_peek(2);
									List<Campo> campos = new ArrayList<Campo>();
									for (int i=0; i<lista.size();i++) {
										campos.add(new Campo(l(), c(), lista.get(i).getNombre(), (Tipo) val_peek(1)));
									}
									yyval = campos;
								}
break;
case 29:
//#line 209 "../../src/sintactico/sintactico.y"
{ yyval = new SentWhile(l(), c(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(1)); }
break;
case 30:
//#line 210 "../../src/sintactico/sintactico.y"
{ yyval = new SentWhile(l(), c(), (Expresion)val_peek(3), (List<Sentencia>)val_peek(1)); }
break;
case 31:
//#line 213 "../../src/sintactico/sintactico.y"
{ yyval = new SentIf(l(), c(), (Expresion)val_peek(5), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 32:
//#line 214 "../../src/sintactico/sintactico.y"
{ yyval = new SentIf(l(), c(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 33:
//#line 217 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 34:
//#line 218 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 35:
//#line 219 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 36:
//#line 222 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 37:
//#line 223 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 38:
//#line 226 "../../src/sintactico/sintactico.y"
{
											List<Sentencia> lista = (List<Sentencia>) val_peek(1);
											lista.add((Sentencia) val_peek(0));
											yyval = lista;
										}
break;
case 39:
//#line 231 "../../src/sintactico/sintactico.y"
{
					  						List<Sentencia>lista = new ArrayList<Sentencia>();
					  						lista.add((Sentencia) val_peek(0));
					  						yyval = lista;
					  					}
break;
case 40:
//#line 238 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(l(), c(), (List<Expresion>) val_peek(2)); }
break;
case 41:
//#line 239 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(l(), c(), (List<Expresion>) val_peek(2)); }
break;
case 42:
//#line 240 "../../src/sintactico/sintactico.y"
{ yyval = new Asignacion(l(), c(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 43:
//#line 241 "../../src/sintactico/sintactico.y"
{ yyval = new SentReturn(l(), c(), (Expresion) val_peek(1)); }
break;
case 44:
//#line 242 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 45:
//#line 243 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 46:
//#line 244 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(3)), new ArrayList<Expresion>()); }
break;
case 47:
//#line 245 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(4)), (List<Expresion>) val_peek(2)); }
break;
case 48:
//#line 246 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaBinaria(l(), c(), (Expresion) val_peek(3), (String) val_peek(2), (Expresion) val_peek(1)); }
break;
case 49:
//#line 247 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaBinaria(l(), c(), (Expresion) val_peek(3), (String) val_peek(2), (Expresion) val_peek(1)); }
break;
case 50:
//#line 250 "../../src/sintactico/sintactico.y"
{
													List<Expresion> lista = (List<Expresion>) val_peek(2);
													lista.add((Expresion) val_peek(0));
													yyval = lista;
												}
break;
case 51:
//#line 255 "../../src/sintactico/sintactico.y"
{
		  											List<Expresion>lista = new ArrayList<Expresion>();
		  											lista.add((Expresion) val_peek(0));
		  											yyval = lista;
		  										}
break;
case 52:
//#line 262 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0)); }
break;
case 53:
//#line 263 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0)); }
break;
case 54:
//#line 264 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0)); }
break;
case 55:
//#line 265 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0)); }
break;
case 56:
//#line 266 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0)); }
break;
case 57:
//#line 267 "../../src/sintactico/sintactico.y"
{ yyval = new LogicaBinaria(l(), c(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0)); }
break;
case 58:
//#line 268 "../../src/sintactico/sintactico.y"
{ yyval = new LogicaBinaria(l(), c(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0)); }
break;
case 59:
//#line 269 "../../src/sintactico/sintactico.y"
{ yyval = new LogicaUnaria(l(), c(), (Expresion) val_peek(0)); }
break;
case 60:
//#line 270 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0)); }
break;
case 61:
//#line 271 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0)); }
break;
case 62:
//#line 272 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0)); }
break;
case 63:
//#line 273 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0)); }
break;
case 64:
//#line 274 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0)); }
break;
case 65:
//#line 275 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0)); }
break;
case 66:
//#line 276 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(l(), c(), (Expresion) val_peek(0)); }
break;
case 67:
//#line 277 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 68:
//#line 278 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(l(), c(), (String) val_peek(0)); }
break;
case 69:
//#line 279 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(l(), c(), (int) val_peek(0)); }
break;
case 70:
//#line 280 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralDecimal(l(), c(), (float) val_peek(0)); }
break;
case 71:
//#line 281 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralChar(l(), c(), ((String) val_peek(0))); }
break;
case 72:
//#line 282 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(3)), (List<Expresion>) val_peek(1)); }
break;
case 73:
//#line 283 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(2)), new ArrayList<Expresion>()); }
break;
case 74:
//#line 284 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(l(), c(), (Tipo)val_peek(3), (Expresion) val_peek(1)); }
break;
case 75:
//#line 285 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoArray(l(), c(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 76:
//#line 286 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampo(l(), c(), (Expresion) val_peek(2), (String) val_peek(0)); }
break;
case 77:
//#line 287 "../../src/sintactico/sintactico.y"
{ yyval = new ExpresionExamen(l(), c(), (Expresion) val_peek(2), (String) val_peek(1), (Expresion) val_peek(0)); }
break;
case 78:
//#line 288 "../../src/sintactico/sintactico.y"
{ yyval = new ExpresionExamen(l(), c(), (Expresion) val_peek(2), (String) val_peek(1), (Expresion) val_peek(0)); }
break;
//#line 1248 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
