package grammar;
import java_cup.runtime.*;
      
%%

%class Lexer
%line
%column
%cup

%{   
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

/* new line */
LineTerminator = \r|\n|\r\n
   
WhiteSpace     = {LineTerminator} | [ \t\f]
   
dec_int_lit = 0 | [1-9][0-9]*
dec_int_id = [A-Za-z_][A-Za-z_0-9]*
   
%%
/* ------------------------Lexical Rules Section---------------------- */
   
   
<YYINITIAL> {
    ";"                { return symbol(sym.SEMI); }
    "+"                { return symbol(sym.PLUS); }
    "-"                { return symbol(sym.MINUS); }
    "*"                { return symbol(sym.TIMES); }
    "/"                { return symbol(sym.DIVIDE); }
    "%"                { return symbol(sym.MOD); }

    "("                { return symbol(sym.LPAREN); }
    ")"                { return symbol(sym.RPAREN); }
    "["                { return symbol(sym.LBRACK); }
    "]"                { return symbol(sym.RBRACK); }
    "{"                { return symbol(sym.LBRACE); }
    "}"                { return symbol(sym.RBRACE); }

    "RANDOM"           { return symbol(sym.RANDOM); }
    "if"               { return symbol(sym.IF); }    
    "else"             { return symbol(sym.ELSE); }
    "while"            { return symbol(sym.WHILE); }
    "int"              { return symbol(sym.INT); }
    "return"           { return symbol(sym.RETURN); }

    "=="               { return symbol(sym.EQEQ); }
    "="                { return symbol(sym.EQ); }
    "<="               { return symbol(sym.LESSEQ); }
    "<"                { return symbol(sym.LESS); }
    ">="               { return symbol(sym.GREATEREQ); }
    ">"                { return symbol(sym.GREATER); }

    ","                { return symbol(sym.COMMA); }
    "A"                { return symbol(sym.A); }
    "B"                { return symbol(sym.B); }
    "CUR"              { return symbol(sym.CUR); }


    /* number or id */
    {dec_int_lit}      { return symbol(sym.NUMBER, new Integer(yytext())); }
    {dec_int_id}       { return symbol(sym.ID, new Integer(0));}

    {WhiteSpace}       { /* empty */ }   
}

/* 非法字符 */
[^]                    { throw new Error("unknow character <"+yytext()+"> at line" + yyline + " column" + yycolumn ); }
