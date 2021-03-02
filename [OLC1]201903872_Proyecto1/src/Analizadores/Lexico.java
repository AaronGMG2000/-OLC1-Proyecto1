package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"6:9,37,32,6:2,3,6:18,36,2,16,34:2,15,34,40,22,23,30,28,19,29,18,5,33:10,14," +
"17,1,6,4,27,6,35:2,7,35:6,10,35:3,9,8,35:11,24,38,25,34:2,6,35:3,13,35:7,12" +
",35,39,35:5,11,35:6,20,26,21,31,6:65409,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,49,
"0,1,2,1,3,1,4,1,5,1,6,7,1:17,8,1:5,9,1:3,10,11,12,13,14,15,16,17,18:2")[0];

	private int yy_nxt[][] = unpackFromString(19,41,
"1,2,3,4,5,6,7,8,36:3,40,36:2,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24" +
",25,26,27,28,37,36,4:2,41,36,37,-1:43,35,-1:41,4,-1:32,4:2,-1:8,29,-1:43,39" +
",-1:47,30,-1:61,43,-1:5,29:2,-1,29:28,-1,29:8,-1,48,44,48,45,48:36,-1:9,46," +
"-1:43,42,-1:44,31,-1:22,31:2,-1:13,32,-1:43,33,-1:25,35:3,38,35:36,-1:4,35," +
"-1:46,34,-1:31,48,44,48,47,48:36");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.MAYOR,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.MENOR,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.DIAGONAL,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.LETRA,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.DOSPT,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.SIGNO,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.COMILLA,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.PT,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.LLAVEIZ,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.LLAVEDER,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.CORDER,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.DISYUN,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.INTERR,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.GUION,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.SEP,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{yychar=1;}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.DIGITO,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.FIN,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.ESPECIAL,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.TLD,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.ESPACIO,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.CONJ,yyline,yychar, yytext());}
					case -35:
						break;
					case 36:
						{return new Symbol(sym.LETRA,yyline,yychar, yytext());}
					case -36:
						break;
					case 37:
						{return new Symbol(sym.SIGNO,yyline,yychar, yytext());}
					case -37:
						break;
					case 38:
						{}
					case -38:
						break;
					case 40:
						{return new Symbol(sym.LETRA,yyline,yychar, yytext());}
					case -39:
						break;
					case 41:
						{return new Symbol(sym.SIGNO,yyline,yychar, yytext());}
					case -40:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
