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
//#line 31 "Parser.java"

public class Parser {

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

	// ########## MESSAGES ##########
	// ###############################################################
	// method: debug
	// ###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

	// ########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
	// ###############################################################
	// methods: state stack push,pop,drop,peek
	// ###############################################################

	final void state_push(int state) {
		try {
			stateptr++;
			statestk[stateptr] = state;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = statestk.length;
			int newsize = oldsize * 2;
			int[] newstack = new int[newsize];
			System.arraycopy(statestk, 0, newstack, 0, oldsize);
			statestk = newstack;
			statestk[stateptr] = state;
		}
	}

	final int state_pop() {
		return statestk[stateptr--];
	}

	final void state_drop(int cnt) {
		stateptr -= cnt;
	}

	final int state_peek(int relative) {
		return statestk[stateptr - relative];
	}

	// ###############################################################
	// method: init_stacks : allocate and prepare stacks
	// ###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

	// ###############################################################
	// method: dump_stacks : show n levels of the stacks
	// ###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
		for (i = 0; i < count; i++)
			System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
		System.out.println("======================");
	}

	// ########## SEMANTIC VALUES ##########
	// ## **user defined:Object
	String yytext;// user variable to return contextual strings
	Object yyval; // used to return semantic vals from action routines
	Object yylval;// the 'lval' (result) I got from yylex()
	Object valstk[] = new Object[YYSTACKSIZE];
	int valptr;

	// ###############################################################
	// methods: value stack push,pop,drop,peek.
	// ###############################################################
	final void val_init() {
		yyval = new Object();
		yylval = new Object();
		valptr = -1;
	}

	final void val_push(Object val) {
		try {
			valptr++;
			valstk[valptr] = val;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = valstk.length;
			int newsize = oldsize * 2;
			Object[] newstack = new Object[newsize];
			System.arraycopy(valstk, 0, newstack, 0, oldsize);
			valstk = newstack;
			valstk[valptr] = val;
		}
	}

	final Object val_pop() {
		return valstk[valptr--];
	}

	final void val_drop(int cnt) {
		valptr -= cnt;
	}

	final Object val_peek(int relative) {
		return valstk[valptr - relative];
	}

	final Object dup_yyval(Object val) {
		return val;
	}

	// #### end semantic value section ####
	public final static short CTE_ENTERA = 257;
	public final static short CTE_DECIMAL = 258;
	public final static short LLA_AB = 259;
	public final static short LLA_CE = 260;
	public final static short PAR_AB = 261;
	public final static short PAR_CE = 262;
	public final static short COR_AB = 263;
	public final static short COR_CE = 264;
	public final static short CHAR = 265;
	public final static short IDENT = 266;
	public final static short OP_MAS = 267;
	public final static short OP_MENOS = 268;
	public final static short OP_MUL = 269;
	public final static short OP_DIV = 270;
	public final static short OP_MOD = 271;
	public final static short SIM_COMIG = 272;
	public final static short SIM_DIST = 273;
	public final static short SIM_MAIG = 274;
	public final static short SIM_MEIG = 275;
	public final static short SIM_IG = 276;
	public final static short SIM_CO = 277;
	public final static short SIM_MA = 278;
	public final static short SIM_PC = 279;
	public final static short SIM_ME = 280;
	public final static short BOOL_AND = 281;
	public final static short BOOL_OR = 282;
	public final static short BOOL_NOT = 283;
	public final static short RES_READ = 284;
	public final static short RES_WRITE = 285;
	public final static short RES_WHILE = 286;
	public final static short RES_IF = 287;
	public final static short RES_ELSE = 288;
	public final static short RES_INT = 289;
	public final static short RES_F32 = 290;
	public final static short RES_CHAR = 291;
	public final static short RES_VAR = 292;
	public final static short RES_STRUCT = 293;
	public final static short RES_RET = 294;
	public final static short RES_FUNC = 295;
	public final static short RES_MAIN = 296;
	public final static short RES_VOID = 297;
	public final static short SIM_PTO = 298;
	public final static short MEN_IG = 299;
	public final static short YYERRCODE = 256;
	final static short yylhs[] = { -1, 0, 2, 1, 1, 4, 4, 7, 7, 5, 8, 8, 6, 6, 3, 10, 10, 12, 12, 13, 9, 9, 9, 9, 9, 14,
			14, 15, 16, 16, 19, 19, 20, 20, 20, 11, 11, 18, 18, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 17, 17, 17, 17,
			17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, };
	final static short yylen[] = { 2, 2, 7, 2, 0, 1, 1, 2, 0, 4, 3, 1, 9, 8, 2, 1, 0, 3, 1, 2, 1, 1, 1, 4, 4, 2, 1, 3,
			7, 5, 8, 6, 4, 6, 0, 1, 0, 2, 1, 5, 5, 4, 3, 1, 1, 4, 5, 3, 1, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 2,
			3, 1, 1, 1, 1, 4, 3, 4, 4, 3, };
	final static short yydefred[] = { 4, 0, 0, 0, 0, 1, 3, 5, 6, 11, 0, 0, 0, 0, 0, 20, 21, 22, 0, 0, 0, 0, 0, 10, 0, 9,
			0, 0, 0, 18, 0, 0, 0, 0, 26, 19, 0, 0, 8, 23, 0, 24, 25, 8, 0, 17, 0, 0, 27, 0, 8, 2, 66, 67, 0, 68, 0, 0,
			0, 0, 0, 0, 0, 0, 7, 0, 14, 43, 0, 0, 44, 38, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 12, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 73, 70, 0, 45, 0, 0, 0, 0, 0, 0, 0, 0, 71, 72, 41, 69, 46, 0, 40, 39, 0, 29, 0, 0, 0, 0,
			0, 31, 28, 0, 0, 0, 30, 0, 0, 32, 0, 0, 33, };
	final static short yydgoto[] = { 1, 2, 5, 46, 6, 7, 8, 47, 32, 65, 27, 66, 28, 29, 33, 34, 67, 68, 69, 70, 160, 71,
			109, };
	final static short yysindex[] = { 0, 0, -226, -263, -264, 0, 0, 0, 0, 0, -209, -236, -232, -250, -211, 0, 0, 0,
			-199, -214, -196, -186, -168, 0, -263, 0, -161, -183, -158, 0, -133, -161, -209, -254, 0, 0, -169, -196, 0,
			0, -146, 0, 0, 0, -115, 0, -113, -74, 0, -109, 0, 0, 0, 0, 298, 0, -116, 298, 298, -108, -107, 310, 347,
			298, 0, -106, 0, 0, 351, 127, 0, 0, 0, -104, -103, 160, 249, -248, -248, 298, 298, 298, -132, 298, -100,
			383, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, -114, 0, 0, 261, 0,
			-122, 509, -246, -203, -191, 181, 127, 202, 127, 0, 223, 404, -207, -207, -248, -248, -248, -249, -249,
			-249, -249, 425, -249, -249, 546, 546, 0, 0, -190, 0, -119, 298, -118, -117, -95, -61, -94, -23, 0, 0, 0, 0,
			0, 509, 0, 0, 127, 0, 127, -112, -11, 27, -136, 0, 0, -112, 127, -85, 0, 39, -80, 0, 127, 77, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -77, 0, 0, 0, 0, 0, 0,
			0, -76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -72, 0, 0, 0, 0, 0, 0, 0, 0, 446, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, -70, 0, 0, 0, 0, -164, 0, 0, 578, 602, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 467, -185, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 698, 722, 626, 650, 674,
			746, 770, 794, 818, 0, 842, 866, 873, 894, 0, 0, 0, 0, 488, 0, 0, 0, 530, 0, 530, 0, 0, 0, 0, 0, 0, -184, 0,
			0, 0, 0, 0, 89, 0, 0, 0, 0, 0, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	final static short yygindex[] = { 0, 0, 0, -26, 0, 146, 0, 0, 192, 355, 0, 0, 0, 164, 0, 170, 0, -53, -102, 0, 44,
			-69, -22, };
	final static int YYTABLESIZE = 1176;
	static short yytable[];
	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 103, 75, 11, 9, 77, 78, 41, 22, 82, 84, 85, 142, 9, 144, 87, 87, 137, 49, 88, 89, 90,
				91, 92, 108, 73, 20, 108, 108, 112, 21, 114, 138, 12, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126,
				127, 128, 129, 130, 131, 132, 102, 102, 157, 108, 158, 13, 23, 87, 110, 111, 139, 24, 166, 90, 91, 92,
				25, 3, 170, 14, 4, 26, 140, 148, 103, 138, 103, 30, 48, 47, 36, 15, 16, 17, 135, 18, 150, 138, 138, 103,
				103, 43, 102, 48, 47, 13, 65, 31, 103, 65, 65, 65, 103, 13, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
				65, 65, 65, 65, 65, 37, 15, 16, 17, 163, 18, 164, 38, 113, 15, 16, 17, 87, 18, 48, 65, 88, 89, 90, 91,
				92, 93, 94, 95, 96, 50, 76, 98, 51, 99, 100, 101, 72, 133, 79, 80, 86, 104, 136, 105, 115, 149, 151,
				152, 87, 153, 155, 102, 88, 89, 90, 91, 92, 93, 94, 95, 96, 159, 167, 98, 169, 99, 100, 101, 52, 53, 16,
				15, 54, 36, 13, 35, 55, 56, 64, 57, 10, 52, 53, 102, 154, 54, 45, 13, 42, 55, 56, 165, 57, 0, 58, 59,
				60, 61, 62, 0, 15, 16, 17, 3, 18, 63, 0, 58, 59, 60, 61, 62, 0, 15, 16, 17, 0, 18, 63, 52, 53, 0, 156,
				54, 0, 13, 0, 55, 56, 0, 57, 52, 53, 0, 161, 54, 0, 13, 0, 55, 56, 0, 57, 0, 0, 58, 59, 60, 61, 62, 0,
				15, 16, 17, 0, 18, 63, 58, 59, 60, 61, 62, 0, 15, 16, 17, 0, 18, 63, 52, 53, 0, 162, 54, 0, 13, 0, 55,
				56, 0, 57, 52, 53, 0, 168, 54, 0, 13, 0, 55, 56, 0, 57, 0, 0, 58, 59, 60, 61, 62, 0, 15, 16, 17, 0, 18,
				63, 58, 59, 60, 61, 62, 0, 15, 16, 17, 0, 18, 63, 52, 53, 0, 171, 54, 0, 13, 0, 55, 56, 0, 57, 34, 34,
				0, 34, 34, 0, 34, 0, 34, 34, 0, 34, 0, 0, 58, 59, 60, 61, 62, 19, 15, 16, 17, 0, 18, 63, 34, 34, 34, 34,
				34, 0, 34, 34, 34, 35, 34, 34, 52, 53, 39, 40, 54, 0, 13, 44, 55, 56, 0, 57, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 58, 59, 60, 61, 62, 0, 15, 16, 17, 0, 18, 63, 106, 87, 0, 0, 0, 88, 89, 90, 91, 92, 93,
				94, 95, 96, 0, 0, 98, 0, 99, 100, 101, 141, 87, 0, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 102, 98,
				0, 99, 100, 101, 143, 87, 0, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 102, 98, 0, 99, 100, 101, 145,
				87, 0, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 102, 98, 0, 99, 100, 101, 52, 53, 0, 0, 54, 107, 13,
				0, 55, 74, 0, 57, 52, 53, 0, 102, 54, 134, 13, 0, 55, 74, 0, 57, 0, 0, 58, 0, 0, 0, 0, 0, 15, 16, 17, 0,
				18, 0, 58, 0, 0, 0, 0, 0, 15, 16, 17, 0, 18, 52, 53, 0, 0, 54, 0, 13, 0, 55, 74, 0, 57, 52, 53, 0, 0,
				81, 0, 13, 0, 55, 74, 0, 57, 0, 0, 58, 0, 0, 0, 0, 0, 15, 16, 17, 0, 18, 0, 58, 0, 0, 0, 0, 0, 15, 16,
				17, 0, 18, 52, 53, 0, 0, 83, 0, 13, 0, 55, 74, 87, 57, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 0,
				98, 58, 99, 100, 101, 0, 0, 15, 16, 17, 0, 18, 0, 0, 0, 0, 0, 87, 0, 0, 102, 88, 89, 90, 91, 92, 93, 94,
				95, 96, 0, 0, 98, 116, 99, 100, 101, 0, 87, 146, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 102, 98,
				0, 99, 100, 101, 0, 87, 0, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 102, 98, 147, 99, 100, 101, 0,
				65, 0, 0, 0, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 102, 65, 0, 65, 65, 65, 0, 70, 0, 0, 0, 70, 70, 70,
				70, 70, 70, 70, 70, 70, 70, 65, 70, 0, 70, 70, 70, 0, 69, 0, 0, 0, 69, 69, 69, 69, 69, 69, 69, 69, 69,
				69, 70, 69, 0, 69, 69, 69, 0, 87, 0, 0, 0, 88, 89, 90, 91, 92, 93, 94, 95, 96, 0, 69, 98, 0, 99, 100,
				101, 0, 64, 0, 0, 0, 64, 64, 64, 64, 64, 64, 64, 64, 64, 0, 102, 64, 87, 64, 64, 64, 88, 89, 90, 91, 92,
				93, 94, 95, 96, 0, 0, 98, 0, 99, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 63, 0, 0, 63, 0, 63, 0, 102, 63, 63, 63,
				63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 56, 0, 0, 56, 0, 56, 0, 0, 56, 56, 56, 56, 56, 56,
				56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 51, 0, 0, 51, 0, 51, 0, 0, 51, 51, 51, 51, 51, 51, 51, 51, 51,
				51, 51, 51, 51, 51, 51, 51, 52, 0, 0, 52, 0, 52, 0, 0, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52,
				52, 52, 52, 52, 53, 0, 0, 53, 0, 53, 0, 0, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53,
				53, 49, 0, 0, 49, 0, 49, 0, 0, 49, 49, 0, 0, 0, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 50, 0, 0,
				50, 0, 50, 0, 0, 50, 50, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 62, 0, 0, 62, 0, 62, 0, 0,
				0, 0, 0, 0, 0, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 61, 0, 0, 61, 0, 61, 0, 0, 0, 0, 0, 0, 0, 61,
				61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 59, 0, 0, 59, 0, 59, 0, 0, 0, 0, 0, 0, 0, 59, 59, 59, 59, 59,
				59, 59, 59, 59, 59, 59, 60, 0, 0, 60, 0, 60, 0, 0, 0, 0, 0, 0, 0, 60, 60, 60, 60, 60, 60, 60, 60, 60,
				60, 60, 57, 0, 0, 57, 0, 57, 0, 0, 0, 0, 0, 0, 0, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 58, 0, 0,
				58, 0, 58, 0, 54, 0, 0, 54, 0, 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 54, 54, 0, 54, 55, 54,
				54, 55, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 55, 0, 55, 0, 55, 55, };
	}

	static short yycheck[];
	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 69, 54, 266, 266, 57, 58, 260, 257, 61, 62, 63, 113, 266, 115, 263, 263, 262, 43, 267,
				268, 269, 270, 271, 76, 50, 261, 79, 80, 81, 261, 83, 277, 296, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
				96, 97, 98, 99, 100, 101, 298, 298, 153, 105, 155, 263, 266, 263, 79, 80, 262, 259, 163, 269, 270, 271,
				279, 292, 169, 277, 295, 266, 262, 262, 142, 277, 144, 262, 262, 262, 262, 289, 290, 291, 105, 293, 138,
				277, 277, 157, 158, 259, 298, 277, 277, 263, 259, 264, 166, 262, 263, 264, 170, 263, 267, 268, 269, 270,
				271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 277, 289, 290, 291, 259, 293, 261, 259, 259,
				289, 290, 291, 263, 293, 279, 298, 267, 268, 269, 270, 271, 272, 273, 274, 275, 259, 261, 278, 260, 280,
				281, 282, 260, 266, 261, 261, 261, 260, 279, 261, 259, 279, 279, 279, 263, 259, 259, 298, 267, 268, 269,
				270, 271, 272, 273, 274, 275, 288, 262, 278, 259, 280, 281, 282, 257, 258, 262, 262, 261, 260, 263, 260,
				265, 266, 47, 268, 3, 257, 258, 298, 260, 261, 37, 263, 33, 265, 266, 162, 268, -1, 283, 284, 285, 286,
				287, -1, 289, 290, 291, 292, 293, 294, -1, 283, 284, 285, 286, 287, -1, 289, 290, 291, -1, 293, 294,
				257, 258, -1, 260, 261, -1, 263, -1, 265, 266, -1, 268, 257, 258, -1, 260, 261, -1, 263, -1, 265, 266,
				-1, 268, -1, -1, 283, 284, 285, 286, 287, -1, 289, 290, 291, -1, 293, 294, 283, 284, 285, 286, 287, -1,
				289, 290, 291, -1, 293, 294, 257, 258, -1, 260, 261, -1, 263, -1, 265, 266, -1, 268, 257, 258, -1, 260,
				261, -1, 263, -1, 265, 266, -1, 268, -1, -1, 283, 284, 285, 286, 287, -1, 289, 290, 291, -1, 293, 294,
				283, 284, 285, 286, 287, -1, 289, 290, 291, -1, 293, 294, 257, 258, -1, 260, 261, -1, 263, -1, 265, 266,
				-1, 268, 257, 258, -1, 260, 261, -1, 263, -1, 265, 266, -1, 268, -1, -1, 283, 284, 285, 286, 287, 10,
				289, 290, 291, -1, 293, 294, 283, 284, 285, 286, 287, -1, 289, 290, 291, 26, 293, 294, 257, 258, 31, 32,
				261, -1, 263, 36, 265, 266, -1, 268, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 283, 284,
				285, 286, 287, -1, 289, 290, 291, -1, 293, 294, 262, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273,
				274, 275, -1, -1, 278, -1, 280, 281, 282, 262, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274,
				275, -1, 298, 278, -1, 280, 281, 282, 262, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275,
				-1, 298, 278, -1, 280, 281, 282, 262, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, -1,
				298, 278, -1, 280, 281, 282, 257, 258, -1, -1, 261, 262, 263, -1, 265, 266, -1, 268, 257, 258, -1, 298,
				261, 262, 263, -1, 265, 266, -1, 268, -1, -1, 283, -1, -1, -1, -1, -1, 289, 290, 291, -1, 293, -1, 283,
				-1, -1, -1, -1, -1, 289, 290, 291, -1, 293, 257, 258, -1, -1, 261, -1, 263, -1, 265, 266, -1, 268, 257,
				258, -1, -1, 261, -1, 263, -1, 265, 266, -1, 268, -1, -1, 283, -1, -1, -1, -1, -1, 289, 290, 291, -1,
				293, -1, 283, -1, -1, -1, -1, -1, 289, 290, 291, -1, 293, 257, 258, -1, -1, 261, -1, 263, -1, 265, 266,
				263, 268, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, -1, 278, 283, 280, 281, 282, -1, -1,
				289, 290, 291, -1, 293, -1, -1, -1, -1, -1, 263, -1, -1, 298, 267, 268, 269, 270, 271, 272, 273, 274,
				275, -1, -1, 278, 279, 280, 281, 282, -1, 263, 264, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275,
				-1, 298, 278, -1, 280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, -1,
				298, 278, 279, 280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276,
				298, 278, -1, 280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 298,
				278, -1, 280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 298, 278,
				-1, 280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, -1, 298, 278, -1,
				280, 281, 282, -1, 263, -1, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274, 275, -1, 298, 278, 263, 280,
				281, 282, 267, 268, 269, 270, 271, 272, 273, 274, 275, -1, -1, 278, -1, 280, -1, 298, -1, -1, -1, -1,
				-1, -1, -1, -1, 259, -1, -1, 262, -1, 264, -1, 298, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276,
				277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264, -1, -1, 267, 268, 269, 270, 271, 272, 273, 274,
				275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264, -1, -1, 267, 268, 269, 270, 271, 272,
				273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264, -1, -1, 267, 268, 269, 270,
				271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264, -1, -1, 267, 268,
				269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264, -1, -1,
				267, 268, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262, -1, 264,
				-1, -1, 267, 268, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1, 262,
				-1, 264, -1, -1, -1, -1, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259, -1, -1,
				262, -1, 264, -1, -1, -1, -1, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 259,
				-1, -1, 262, -1, 264, -1, -1, -1, -1, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282,
				259, -1, -1, 262, -1, 264, -1, -1, -1, -1, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281,
				282, 259, -1, -1, 262, -1, 264, -1, -1, -1, -1, -1, -1, -1, 272, 273, 274, 275, 276, 277, 278, 279, 280,
				281, 282, 259, -1, -1, 262, -1, 264, -1, 259, -1, -1, 262, -1, 264, 272, 273, 274, 275, 276, 277, 278,
				279, 280, 281, 282, 276, 277, -1, 279, 259, 281, 282, 262, -1, 264, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, 276, 277, -1, 279, -1, 281, 282, };
	}

	final static short YYFINAL = 1;
	final static short YYMAXTOKEN = 299;
	final static String yyname[] = { "end-of-file", null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, "CTE_ENTERA", "CTE_DECIMAL", "LLA_AB",
			"LLA_CE", "PAR_AB", "PAR_CE", "COR_AB", "COR_CE", "CHAR", "IDENT", "OP_MAS", "OP_MENOS", "OP_MUL", "OP_DIV",
			"OP_MOD", "SIM_COMIG", "SIM_DIST", "SIM_MAIG", "SIM_MEIG", "SIM_IG", "SIM_CO", "SIM_MA", "SIM_PC", "SIM_ME",
			"BOOL_AND", "BOOL_OR", "BOOL_NOT", "RES_READ", "RES_WRITE", "RES_WHILE", "RES_IF", "RES_ELSE", "RES_INT",
			"RES_F32", "RES_CHAR", "RES_VAR", "RES_STRUCT", "RES_RET", "RES_FUNC", "RES_MAIN", "RES_VOID", "SIM_PTO",
			"MEN_IG", };
	final static String yyrule[] = { "$accept : programa", "programa : definiciones funcmain",
			"funcmain : RES_FUNC RES_MAIN PAR_AB PAR_CE LLA_AB cuerpofuncion LLA_CE",
			"definiciones : definiciones definicion", "definiciones :", "definicion : defvar", "definicion : funcion",
			"defvaropc : defvaropc defvar", "defvaropc :", "defvar : RES_VAR lvar tipo SIM_PC",
			"lvar : lvar SIM_CO IDENT", "lvar : IDENT",
			"funcion : RES_FUNC IDENT PAR_AB parametrosopc PAR_CE tipo LLA_AB cuerpofuncion LLA_CE",
			"funcion : RES_FUNC IDENT PAR_AB parametrosopc PAR_CE LLA_AB cuerpofuncion LLA_CE",
			"cuerpofuncion : defvaropc sentenciasopc", "parametrosopc : parametros", "parametrosopc :",
			"parametros : parametros SIM_CO parametro", "parametros : parametro", "parametro : IDENT tipo",
			"tipo : RES_INT", "tipo : RES_F32", "tipo : RES_CHAR", "tipo : COR_AB CTE_ENTERA COR_CE tipo",
			"tipo : RES_STRUCT LLA_AB campos LLA_CE", "campos : campos campo", "campos : campo",
			"campo : lvar tipo SIM_PC", "while : RES_WHILE PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE",
			"while : RES_WHILE expresion LLA_AB sentencias LLA_CE",
			"if : RES_IF PAR_AB expresion PAR_CE LLA_AB sentencias LLA_CE else",
			"if : RES_IF expresion LLA_AB sentencias LLA_CE else", "else : RES_ELSE LLA_AB sentencias LLA_CE",
			"else : RES_ELSE PAR_AB PAR_CE LLA_AB sentencias LLA_CE", "else :", "sentenciasopc : sentencias",
			"sentenciasopc :", "sentencias : sentencias sentencia", "sentencias : sentencia",
			"sentencia : RES_WRITE PAR_AB expresiones PAR_CE SIM_PC",
			"sentencia : RES_READ PAR_AB expresiones PAR_CE SIM_PC", "sentencia : expresion SIM_IG expresion SIM_PC",
			"sentencia : RES_RET expresion SIM_PC", "sentencia : while", "sentencia : if",
			"sentencia : IDENT PAR_AB PAR_CE SIM_PC", "sentencia : IDENT PAR_AB expresiones PAR_CE SIM_PC",
			"expresiones : expresiones SIM_CO expresion", "expresiones : expresion",
			"expresion : expresion OP_MAS expresion", "expresion : expresion OP_MENOS expresion",
			"expresion : expresion OP_MUL expresion", "expresion : expresion OP_DIV expresion",
			"expresion : expresion OP_MOD expresion", "expresion : expresion BOOL_AND expresion",
			"expresion : expresion BOOL_OR expresion", "expresion : BOOL_NOT expresion",
			"expresion : expresion SIM_MA expresion", "expresion : expresion SIM_ME expresion",
			"expresion : expresion SIM_MAIG expresion", "expresion : expresion SIM_MEIG expresion",
			"expresion : expresion SIM_DIST expresion", "expresion : expresion SIM_COMIG expresion",
			"expresion : OP_MENOS expresion", "expresion : PAR_AB expresion PAR_CE", "expresion : IDENT",
			"expresion : CTE_ENTERA", "expresion : CTE_DECIMAL", "expresion : CHAR",
			"expresion : IDENT PAR_AB expresiones PAR_CE", "expresion : IDENT PAR_AB PAR_CE",
			"expresion : tipo PAR_AB expresion PAR_CE", "expresion : expresion COR_AB expresion COR_CE",
			"expresion : expresion SIM_PTO IDENT", };

	// #line 273 "../../src/sintactico/sintactico.y"

	// * Código Java
	// * Se crea una clase "Parser", lo que aquí ubiquemos será:
	// - Atributos, si son variables
	// - Métodos, si son funciones
	// de la clase "Parser"

	// * Estamos obligados a implementar:
	// int yylex()
	// void yyerror(String)

	// * Referencia al analizador léxico
	private Lexico lexico;
	private NodoAST ast;

	private int l() {
		return lexico.getLinea();
	}

	private int c() {
		return lexico.getColumna();
	}

	public NodoAST getAST() {
		return ast;
	}

	// * Llamada al analizador léxico
	private int yylex() {
		int token = 0;
		try {
			token = lexico.yylex();
			this.yylval = lexico.getYylval();
		} catch (Throwable e) {
			System.err.println("Error Léxico en línea " + l() + " y columna " + c() + ":\n\t" + e);
		}
		return token;
	}

	// * Manejo de Errores Sintácticos
	public void yyerror(String error) {
		System.err.println("Error Sintáctico en línea " + l() + " y columna " + c() + ":\n\t" + error);
	}

	// * Constructor del Sintáctico
	public Parser(Lexico lexico) {
		this.lexico = lexico;
	}

	// #line 637 "Parser.java"
	// ###############################################################
	// method: yylexdebug : check lexer state
	// ###############################################################
	void yylexdebug(int state, int ch) {
		String s = null;
		if (ch < 0)
			ch = 0;
		if (ch <= YYMAXTOKEN) // check index bounds
			s = yyname[ch]; // now get it
		if (s == null)
			s = "illegal-symbol";
		debug("state " + state + ", reading " + ch + " (" + s + ")");
	}

	// The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

	// ###############################################################
	// method: yyparse : parse input and execute indicated items
	// ###############################################################
	int yyparse() {
		boolean doaction;
		init_stacks();
		yynerrs = 0;
		yyerrflag = 0;
		yychar = -1; // impossible char forces a read
		yystate = 0; // initial state
		state_push(yystate); // save it
		val_push(yylval); // save empty value
		while (true) // until parsing is done, either correctly, or w/error
		{
			doaction = true;
			if (yydebug)
				debug("loop");
			// #### NEXT ACTION (from reduction table)
			for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
				if (yydebug)
					debug("yyn:" + yyn + "  state:" + yystate + "  yychar:" + yychar);
				if (yychar < 0) // we want a char?
				{
					yychar = yylex(); // get next token
					if (yydebug)
						debug(" next yychar:" + yychar);
					// #### ERROR CHECK ####
					if (yychar < 0) // it it didn't work/error
					{
						yychar = 0; // change it to default string (no -1!)
						if (yydebug)
							yylexdebug(yystate, yychar);
					}
				} // yychar<0
				yyn = yysindex[yystate]; // get amount to shift by (shift index)
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {
					if (yydebug)
						debug("state " + yystate + ", shifting to state " + yytable[yyn]);
					// #### NEXT STATE ####
					yystate = yytable[yyn];// we are in a new state
					state_push(yystate); // save it
					val_push(yylval); // push our lval as the input for next rule
					yychar = -1; // since we have 'eaten' a token, say we need another
					if (yyerrflag > 0) // have we recovered an error?
						--yyerrflag; // give ourselves credit
					doaction = false; // but don't process yet
					break; // quit the yyn=0 loop
				}

				yyn = yyrindex[yystate]; // reduce
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) { // we reduced!
					if (yydebug)
						debug("reduce");
					yyn = yytable[yyn];
					doaction = true; // get ready to execute
					break; // drop down to actions
				} else // ERROR RECOVERY
				{
					if (yyerrflag == 0) {
						yyerror("syntax error");
						yynerrs++;
					}
					if (yyerrflag < 3) // low error count?
					{
						yyerrflag = 3;
						while (true) // do until break
						{
							if (stateptr < 0) // check for under & overflow here
							{
								yyerror("stack underflow. aborting..."); // note lower case 's'
								return 1;
							}
							yyn = yysindex[state_peek(0)];
							if ((yyn != 0) && (yyn += YYERRCODE) >= 0 && yyn <= YYTABLESIZE
									&& yycheck[yyn] == YYERRCODE) {
								if (yydebug)
									debug("state " + state_peek(0) + ", error recovery shifting to state "
											+ yytable[yyn] + " ");
								yystate = yytable[yyn];
								state_push(yystate);
								val_push(yylval);
								doaction = false;
								break;
							} else {
								if (yydebug)
									debug("error recovery discarding state " + state_peek(0) + " ");
								if (stateptr < 0) // check for under & overflow here
								{
									yyerror("Stack underflow. aborting..."); // capital 'S'
									return 1;
								}
								state_pop();
								val_pop();
							}
						}
					} else // discard this token
					{
						if (yychar == 0)
							return 1; // yyabort
						if (yydebug) {
							yys = null;
							if (yychar <= YYMAXTOKEN)
								yys = yyname[yychar];
							if (yys == null)
								yys = "illegal-symbol";
							debug("state " + yystate + ", error recovery discards token " + yychar + " (" + yys + ")");
						}
						yychar = -1; // read another
					}
				} // end error recovery
			} // yyn=0 loop
			if (!doaction) // any reason not to proceed?
				continue; // skip action
			yym = yylen[yyn]; // get count of terminals on rhs
			if (yydebug)
				debug("state " + yystate + ", reducing " + yym + " by rule " + yyn + " (" + yyrule[yyn] + ")");
			if (yym > 0) // if count of rhs not 'nil'
				yyval = val_peek(yym - 1); // get current semantic value
			yyval = dup_yyval(yyval); // duplicate yyval if ParserVal is used as semantic value
			switch (yyn) {
			// ########## USER-SUPPLIED ACTIONS ##########
			case 1:
			// #line 78 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> lista = (List<Definicion>) val_peek(1);
				lista.add((Definicion) val_peek(0));
				this.ast = new Programa(l(), c(), lista);
			}
				break;
			case 2:
			// #line 84 "../../src/sintactico/sintactico.y"
			{
				yyval = new DefinicionFuncion(l(), c(),
						new TipoFuncion(l(), c(), new TipoVoid(l(), c()), new ArrayList<DefVariable>()), "main",
						(List<Sentencia>) val_peek(1));
			}
				break;
			case 3:
			// #line 90 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> lista = (List<Definicion>) val_peek(1);
				lista.addAll((List<Definicion>) val_peek(0));
				yyval = lista;
			}
				break;
			case 4:
			// #line 95 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Definicion>();
			}
				break;
			case 5:
			// #line 98 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 6:
			// #line 99 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> lista = new ArrayList<Definicion>();
				lista.add((Definicion) val_peek(0));
				yyval = lista;
			}
				break;
			case 7:
			// #line 106 "../../src/sintactico/sintactico.y"
			{
				List<DefVariable> lista = (List<DefVariable>) val_peek(1);
				lista.addAll((List<DefVariable>) val_peek(0));
				yyval = lista;
			}
				break;
			case 8:
			// #line 111 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<DefVariable>();
			}
				break;
			case 9:
			// #line 114 "../../src/sintactico/sintactico.y"
			{
				List<DefVariable> lista = new ArrayList<DefVariable>();
				for (int i = 0; i < ((List<Variable>) val_peek(2)).size(); i++) {
					lista.add(new DefVariable(l(), c(), ((List<Variable>) val_peek(2)).get(i).getNombre(),
							(Tipo) val_peek(1)));
				}
				yyval = lista;
			}
				break;
			case 10:
			// #line 122 "../../src/sintactico/sintactico.y"
			{
				List<Variable> lista = (List<Variable>) val_peek(2);
				lista.add(new Variable(l(), c(), (String) val_peek(0)));
				yyval = lista;
			}
				break;
			case 11:
			// #line 127 "../../src/sintactico/sintactico.y"
			{
				List<Variable> lista = new ArrayList<Variable>();
				lista.add(new Variable(l(), c(), (String) val_peek(0)));
				yyval = lista;
			}
				break;
			case 12:
			// #line 134 "../../src/sintactico/sintactico.y"
			{
				yyval = new DefinicionFuncion(l(), c(),
						new TipoFuncion(l(), c(), (Tipo) val_peek(3), (List<DefVariable>) val_peek(5)),
						(String) val_peek(7), (List<Sentencia>) val_peek(1));
			}
				break;
			case 13:
			// #line 139 "../../src/sintactico/sintactico.y"
			{
				yyval = new DefinicionFuncion(l(), c(),
						new TipoFuncion(l(), c(), new TipoVoid(l(), c()), (List<DefVariable>) val_peek(4)),
						(String) val_peek(6), (List<Sentencia>) val_peek(1));
			}
				break;
			case 14:
			// #line 146 "../../src/sintactico/sintactico.y"
			{
				List<Sentencia> lista = (List<Sentencia>) val_peek(1);
				lista.addAll((List<Sentencia>) val_peek(0));
				yyval = lista;
			}
				break;
			case 15:
			// #line 152 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 16:
			// #line 153 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<DefVariable>();
			}
				break;
			case 17:
			// #line 156 "../../src/sintactico/sintactico.y"
			{
				List<DefVariable> lista = (List<DefVariable>) val_peek(2);
				lista.add((DefVariable) val_peek(0));
				yyval = lista;
			}
				break;
			case 18:
			// #line 161 "../../src/sintactico/sintactico.y"
			{
				List<DefVariable> lista = new ArrayList<DefVariable>();
				lista.add((DefVariable) val_peek(0));
				yyval = lista;
			}
				break;
			case 19:
			// #line 168 "../../src/sintactico/sintactico.y"
			{
				yyval = new DefVariable(l(), c(), (String) val_peek(1), (Tipo) val_peek(0));
			}
				break;
			case 20:
			// #line 170 "../../src/sintactico/sintactico.y"
			{
				yyval = new TipoEntero(l(), c());
			}
				break;
			case 21:
			// #line 171 "../../src/sintactico/sintactico.y"
			{
				yyval = new TipoFloat32(l(), c());
			}
				break;
			case 22:
			// #line 172 "../../src/sintactico/sintactico.y"
			{
				yyval = new TipoChar(l(), c());
			}
				break;
			case 23:
			// #line 173 "../../src/sintactico/sintactico.y"
			{
				yyval = new TipoArray(l(), c(), (int) val_peek(2), (Tipo) val_peek(0));
			}
				break;
			case 24:
			// #line 174 "../../src/sintactico/sintactico.y"
			{
				yyval = new TipoStruct(l(), c(), (List<Campo>) val_peek(1));
			}
				break;
			case 25:
			// #line 177 "../../src/sintactico/sintactico.y"
			{
				List<Campo> lista = (List<Campo>) val_peek(1);
				lista.addAll((List<Campo>) val_peek(0));
				yyval = lista;
			}
				break;
			case 26:
			// #line 182 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 27:
			// #line 185 "../../src/sintactico/sintactico.y"
			{
				List<Variable> lista = (List<Variable>) val_peek(2);
				List<Campo> campos = new ArrayList<Campo>();
				for (int i = 0; i < lista.size(); i++) {
					campos.add(new Campo(l(), c(), lista.get(i).getNombre(), (Tipo) val_peek(1)));
				}
				yyval = campos;
			}
				break;
			case 28:
			// #line 195 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentWhile(l(), c(), (Expresion) val_peek(4), (List<Sentencia>) val_peek(1));
			}
				break;
			case 29:
			// #line 196 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentWhile(l(), c(), (Expresion) val_peek(3), (List<Sentencia>) val_peek(1));
			}
				break;
			case 30:
			// #line 199 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentIf(l(), c(), (Expresion) val_peek(5), (List<Sentencia>) val_peek(2),
						(List<Sentencia>) val_peek(0));
			}
				break;
			case 31:
			// #line 200 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentIf(l(), c(), (Expresion) val_peek(4), (List<Sentencia>) val_peek(2),
						(List<Sentencia>) val_peek(0));
			}
				break;
			case 32:
			// #line 203 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 33:
			// #line 204 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 34:
			// #line 205 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
			}
				break;
			case 35:
			// #line 208 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 36:
			// #line 209 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
			}
				break;
			case 37:
			// #line 212 "../../src/sintactico/sintactico.y"
			{
				List<Sentencia> lista = (List<Sentencia>) val_peek(1);
				lista.add((Sentencia) val_peek(0));
				yyval = lista;
			}
				break;
			case 38:
			// #line 217 "../../src/sintactico/sintactico.y"
			{
				List<Sentencia> lista = new ArrayList<Sentencia>();
				lista.add((Sentencia) val_peek(0));
				yyval = lista;
			}
				break;
			case 39:
			// #line 224 "../../src/sintactico/sintactico.y"
			{
				yyval = new Escritura(l(), c(), (List<Expresion>) val_peek(2));
			}
				break;
			case 40:
			// #line 225 "../../src/sintactico/sintactico.y"
			{
				yyval = new Lectura(l(), c(), (List<Expresion>) val_peek(2));
			}
				break;
			case 41:
			// #line 226 "../../src/sintactico/sintactico.y"
			{
				yyval = new Asignacion(l(), c(), (Expresion) val_peek(3), (Expresion) val_peek(1));
			}
				break;
			case 42:
			// #line 227 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentReturn(l(), c(), (Expresion) val_peek(1));
			}
				break;
			case 43:
			// #line 228 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 44:
			// #line 229 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 45:
			// #line 230 "../../src/sintactico/sintactico.y"
			{
				yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(3)),
						new ArrayList<Expresion>());
			}
				break;
			case 46:
			// #line 231 "../../src/sintactico/sintactico.y"
			{
				yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(4)),
						(List<Expresion>) val_peek(2));
			}
				break;
			case 47:
			// #line 234 "../../src/sintactico/sintactico.y"
			{
				List<Expresion> lista = (List<Expresion>) val_peek(2);
				lista.add((Expresion) val_peek(0));
				yyval = lista;
			}
				break;
			case 48:
			// #line 239 "../../src/sintactico/sintactico.y"
			{
				List<Expresion> lista = new ArrayList<Expresion>();
				lista.add((Expresion) val_peek(0));
				yyval = lista;
			}
				break;
			case 49:
			// #line 246 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));
			}
				break;
			case 50:
			// #line 247 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));
			}
				break;
			case 51:
			// #line 248 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));
			}
				break;
			case 52:
			// #line 249 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));
			}
				break;
			case 53:
			// #line 250 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(l(), c(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));
			}
				break;
			case 54:
			// #line 251 "../../src/sintactico/sintactico.y"
			{
				yyval = new LogicaBinaria(l(), c(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));
			}
				break;
			case 55:
			// #line 252 "../../src/sintactico/sintactico.y"
			{
				yyval = new LogicaBinaria(l(), c(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));
			}
				break;
			case 56:
			// #line 253 "../../src/sintactico/sintactico.y"
			{
				yyval = new LogicaUnaria(l(), c(), (Expresion) val_peek(0));
			}
				break;
			case 57:
			// #line 254 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));
			}
				break;
			case 58:
			// #line 255 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));
			}
				break;
			case 59:
			// #line 256 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));
			}
				break;
			case 60:
			// #line 257 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));
			}
				break;
			case 61:
			// #line 258 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));
			}
				break;
			case 62:
			// #line 259 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(l(), c(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));
			}
				break;
			case 63:
			// #line 260 "../../src/sintactico/sintactico.y"
			{
				yyval = new MenosUnario(l(), c(), (Expresion) val_peek(0));
			}
				break;
			case 64:
			// #line 261 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 65:
			// #line 262 "../../src/sintactico/sintactico.y"
			{
				yyval = new Variable(l(), c(), (String) val_peek(0));
			}
				break;
			case 66:
			// #line 263 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralEntero(l(), c(), (int) val_peek(0));
			}
				break;
			case 67:
			// #line 264 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralDecimal(l(), c(), (float) val_peek(0));
			}
				break;
			case 68:
			// #line 265 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralChar(l(), c(), ((String) val_peek(0)).charAt(0));
			}
				break;
			case 69:
			// #line 266 "../../src/sintactico/sintactico.y"
			{
				yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(3)),
						(List<Expresion>) val_peek(1));
			}
				break;
			case 70:
			// #line 267 "../../src/sintactico/sintactico.y"
			{
				yyval = new InvocacionFuncion(l(), c(), new Variable(l(), c(), (String) val_peek(2)),
						new ArrayList<Expresion>());
			}
				break;
			case 71:
			// #line 268 "../../src/sintactico/sintactico.y"
			{
				yyval = new Cast(l(), c(), (Tipo) val_peek(3), (Expresion) val_peek(1));
			}
				break;
			case 72:
			// #line 269 "../../src/sintactico/sintactico.y"
			{
				yyval = new AccesoArray(l(), c(), (Expresion) val_peek(3), (Expresion) val_peek(1));
			}
				break;
			case 73:
			// #line 270 "../../src/sintactico/sintactico.y"
			{
				yyval = new AccesoCampo(l(), c(), (Expresion) val_peek(2), (String) val_peek(0));
			}
				break;
			// #line 1159 "Parser.java"
			// ########## END OF USER-SUPPLIED ACTIONS ##########
			}// switch
				// #### Now let's reduce... ####
			if (yydebug)
				debug("reduce");
			state_drop(yym); // we just reduced yylen states
			yystate = state_peek(0); // get new state
			val_drop(yym); // corresponding value drop
			yym = yylhs[yyn]; // select next TERMINAL(on lhs)
			if (yystate == 0 && yym == 0)// done? 'rest' state and at first TERMINAL
			{
				if (yydebug)
					debug("After reduction, shifting from state 0 to state " + YYFINAL + "");
				yystate = YYFINAL; // explicitly say we're done
				state_push(YYFINAL); // and save it
				val_push(yyval); // also save the semantic value of parsing
				if (yychar < 0) // we want another character?
				{
					yychar = yylex(); // get next character
					if (yychar < 0)
						yychar = 0; // clean, if necessary
					if (yydebug)
						yylexdebug(yystate, yychar);
				}
				if (yychar == 0) // Good exit (if lex returns 0 ;-)
					break; // quit the loop--all DONE
			} // if yystate
			else // else not done yet
			{ // get next state and push, for next yydefred[]
				yyn = yygindex[yym]; // find out where to go
				if ((yyn != 0) && (yyn += yystate) >= 0 && yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
					yystate = yytable[yyn]; // get new state
				else
					yystate = yydgoto[yym]; // else go to new defred
				if (yydebug)
					debug("after reduction, shifting from state " + state_peek(0) + " to state " + yystate + "");
				state_push(yystate); // going again, so push state & val...
				val_push(yyval); // for next action
			}
		} // main loop
		return 0;// yyaccept!!
	}
	// ## end of method parse() ######################################

	// ## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}
	// ## end of method run() ########################################

	// ## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 * 
	 */
	public Parser() {
		// nothing to do
	}

	/**
	 * Create a parser, setting the debug to true or false.
	 * 
	 * @param debugMe
	 *            true for debugging, false for no debug.
	 */
	public Parser(boolean debugMe) {
		yydebug = debugMe;
	}
	// ###############################################################

}
// ################### END OF CLASS ##############################
