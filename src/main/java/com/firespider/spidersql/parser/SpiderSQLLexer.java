// Generated from D:/firespider-spidersql\SpiderSQL.g4 by ANTLR 4.7
package com.firespider.spidersql.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SpiderSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, GET_STR=14, SAVE_STR=15, SCAN_STR=16, 
		DESC_STR=17, PRINT_STR=18, PARALLEL=19, SERIAL=20, ASSIGN=21, SYMBOL=22, 
		C_VAR=23, STRING=24, DOUBLE=25, INT=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "GET_STR", "SAVE_STR", "SCAN_STR", 
		"DESC_STR", "PRINT_STR", "PARALLEL", "SERIAL", "ASSIGN", "SYMBOL", "C_VAR", 
		"STRING", "DOUBLE", "INT", "ID", "ESC", "UNICODE", "HEX", "DIGIT", "A", 
		"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", 
		"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'-['", "']>'", "','", "'('", "')'", "'{'", "'}'", "'['", 
		"']'", "'true'", "'false'", "'null'", null, null, null, null, null, "';'", 
		"'|'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "GET_STR", "SAVE_STR", "SCAN_STR", "DESC_STR", "PRINT_STR", 
		"PARALLEL", "SERIAL", "ASSIGN", "SYMBOL", "C_VAR", "STRING", "DOUBLE", 
		"INT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SpiderSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SpiderSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u0148\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\6\30\u00c1\n\30\r\30\16\30"+
		"\u00c2\3\30\3\30\6\30\u00c7\n\30\r\30\16\30\u00c8\7\30\u00cb\n\30\f\30"+
		"\16\30\u00ce\13\30\3\31\3\31\3\31\7\31\u00d3\n\31\f\31\16\31\u00d6\13"+
		"\31\3\31\3\31\3\32\5\32\u00db\n\32\3\32\6\32\u00de\n\32\r\32\16\32\u00df"+
		"\3\32\3\32\6\32\u00e4\n\32\r\32\16\32\u00e5\6\32\u00e8\n\32\r\32\16\32"+
		"\u00e9\3\33\5\33\u00ed\n\33\3\33\6\33\u00f0\n\33\r\33\16\33\u00f1\3\34"+
		"\6\34\u00f5\n\34\r\34\16\34\u00f6\3\34\7\34\u00fa\n\34\f\34\16\34\u00fd"+
		"\13\34\3\35\3\35\3\35\5\35\u0102\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)"+
		"\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62"+
		"\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3"+
		";\6;\u0143\n;\r;\16;\u0144\3;\3;\2\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2"+
		"U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\35\3\2$\5\2,-//\61\61"+
		"\4\2$$^^\4\2C\\c|\5\2\62;C\\c|\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\3\2"+
		"\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4"+
		"\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSs"+
		"s\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2"+
		"\\\\||\5\2\13\f\17\17\"\"\2\u0137\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2u\3\2\2\2\3w\3\2\2\2\5z\3\2\2\2\7}\3\2\2\2\t\u0080\3\2\2\2\13\u0082"+
		"\3\2\2\2\r\u0084\3\2\2\2\17\u0086\3\2\2\2\21\u0088\3\2\2\2\23\u008a\3"+
		"\2\2\2\25\u008c\3\2\2\2\27\u008e\3\2\2\2\31\u0093\3\2\2\2\33\u0099\3\2"+
		"\2\2\35\u009e\3\2\2\2\37\u00a2\3\2\2\2!\u00a7\3\2\2\2#\u00ac\3\2\2\2%"+
		"\u00b1\3\2\2\2\'\u00b7\3\2\2\2)\u00b9\3\2\2\2+\u00bb\3\2\2\2-\u00bd\3"+
		"\2\2\2/\u00c0\3\2\2\2\61\u00cf\3\2\2\2\63\u00da\3\2\2\2\65\u00ec\3\2\2"+
		"\2\67\u00f4\3\2\2\29\u00fe\3\2\2\2;\u0103\3\2\2\2=\u0109\3\2\2\2?\u010b"+
		"\3\2\2\2A\u010d\3\2\2\2C\u010f\3\2\2\2E\u0111\3\2\2\2G\u0113\3\2\2\2I"+
		"\u0115\3\2\2\2K\u0117\3\2\2\2M\u0119\3\2\2\2O\u011b\3\2\2\2Q\u011d\3\2"+
		"\2\2S\u011f\3\2\2\2U\u0121\3\2\2\2W\u0123\3\2\2\2Y\u0125\3\2\2\2[\u0127"+
		"\3\2\2\2]\u0129\3\2\2\2_\u012b\3\2\2\2a\u012d\3\2\2\2c\u012f\3\2\2\2e"+
		"\u0131\3\2\2\2g\u0133\3\2\2\2i\u0135\3\2\2\2k\u0137\3\2\2\2m\u0139\3\2"+
		"\2\2o\u013b\3\2\2\2q\u013d\3\2\2\2s\u013f\3\2\2\2u\u0142\3\2\2\2wx\7/"+
		"\2\2xy\7@\2\2y\4\3\2\2\2z{\7/\2\2{|\7]\2\2|\6\3\2\2\2}~\7_\2\2~\177\7"+
		"@\2\2\177\b\3\2\2\2\u0080\u0081\7.\2\2\u0081\n\3\2\2\2\u0082\u0083\7*"+
		"\2\2\u0083\f\3\2\2\2\u0084\u0085\7+\2\2\u0085\16\3\2\2\2\u0086\u0087\7"+
		"}\2\2\u0087\20\3\2\2\2\u0088\u0089\7\177\2\2\u0089\22\3\2\2\2\u008a\u008b"+
		"\7]\2\2\u008b\24\3\2\2\2\u008c\u008d\7_\2\2\u008d\26\3\2\2\2\u008e\u008f"+
		"\7v\2\2\u008f\u0090\7t\2\2\u0090\u0091\7w\2\2\u0091\u0092\7g\2\2\u0092"+
		"\30\3\2\2\2\u0093\u0094\7h\2\2\u0094\u0095\7c\2\2\u0095\u0096\7n\2\2\u0096"+
		"\u0097\7u\2\2\u0097\u0098\7g\2\2\u0098\32\3\2\2\2\u0099\u009a\7p\2\2\u009a"+
		"\u009b\7w\2\2\u009b\u009c\7n\2\2\u009c\u009d\7n\2\2\u009d\34\3\2\2\2\u009e"+
		"\u009f\5M\'\2\u009f\u00a0\5I%\2\u00a0\u00a1\5g\64\2\u00a1\36\3\2\2\2\u00a2"+
		"\u00a3\5e\63\2\u00a3\u00a4\5A!\2\u00a4\u00a5\5k\66\2\u00a5\u00a6\5I%\2"+
		"\u00a6 \3\2\2\2\u00a7\u00a8\5e\63\2\u00a8\u00a9\5E#\2\u00a9\u00aa\5A!"+
		"\2\u00aa\u00ab\5[.\2\u00ab\"\3\2\2\2\u00ac\u00ad\5G$\2\u00ad\u00ae\5I"+
		"%\2\u00ae\u00af\5e\63\2\u00af\u00b0\5E#\2\u00b0$\3\2\2\2\u00b1\u00b2\5"+
		"_\60\2\u00b2\u00b3\5c\62\2\u00b3\u00b4\5Q)\2\u00b4\u00b5\5[.\2\u00b5\u00b6"+
		"\5g\64\2\u00b6&\3\2\2\2\u00b7\u00b8\7=\2\2\u00b8(\3\2\2\2\u00b9\u00ba"+
		"\7~\2\2\u00ba*\3\2\2\2\u00bb\u00bc\7<\2\2\u00bc,\3\2\2\2\u00bd\u00be\t"+
		"\2\2\2\u00be.\3\2\2\2\u00bf\u00c1\5\67\34\2\u00c0\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00cc\3\2"+
		"\2\2\u00c4\u00c6\7\60\2\2\u00c5\u00c7\5\67\34\2\u00c6\u00c5\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2"+
		"\2\2\u00ca\u00c4\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\60\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d4\7$\2\2"+
		"\u00d0\u00d3\59\35\2\u00d1\u00d3\n\3\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1"+
		"\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\7$\2\2\u00d8\62\3\2\2\2"+
		"\u00d9\u00db\7/\2\2\u00da\u00d9\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dd"+
		"\3\2\2\2\u00dc\u00de\5? \2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e7\3\2\2\2\u00e1\u00e3\7\60"+
		"\2\2\u00e2\u00e4\5? \2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3"+
		"\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e1\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\64\3\2\2"+
		"\2\u00eb\u00ed\7/\2\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef"+
		"\3\2\2\2\u00ee\u00f0\5? \2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\66\3\2\2\2\u00f3\u00f5\t\4\2"+
		"\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7"+
		"\3\2\2\2\u00f7\u00fb\3\2\2\2\u00f8\u00fa\t\5\2\2\u00f9\u00f8\3\2\2\2\u00fa"+
		"\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc8\3\2\2\2"+
		"\u00fd\u00fb\3\2\2\2\u00fe\u0101\7^\2\2\u00ff\u0102\t\6\2\2\u0100\u0102"+
		"\5;\36\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102:\3\2\2\2\u0103"+
		"\u0104\7w\2\2\u0104\u0105\5=\37\2\u0105\u0106\5=\37\2\u0106\u0107\5=\37"+
		"\2\u0107\u0108\5=\37\2\u0108<\3\2\2\2\u0109\u010a\t\7\2\2\u010a>\3\2\2"+
		"\2\u010b\u010c\t\b\2\2\u010c@\3\2\2\2\u010d\u010e\t\t\2\2\u010eB\3\2\2"+
		"\2\u010f\u0110\t\n\2\2\u0110D\3\2\2\2\u0111\u0112\t\13\2\2\u0112F\3\2"+
		"\2\2\u0113\u0114\t\f\2\2\u0114H\3\2\2\2\u0115\u0116\t\r\2\2\u0116J\3\2"+
		"\2\2\u0117\u0118\t\16\2\2\u0118L\3\2\2\2\u0119\u011a\t\17\2\2\u011aN\3"+
		"\2\2\2\u011b\u011c\t\20\2\2\u011cP\3\2\2\2\u011d\u011e\t\21\2\2\u011e"+
		"R\3\2\2\2\u011f\u0120\t\22\2\2\u0120T\3\2\2\2\u0121\u0122\t\23\2\2\u0122"+
		"V\3\2\2\2\u0123\u0124\t\24\2\2\u0124X\3\2\2\2\u0125\u0126\t\25\2\2\u0126"+
		"Z\3\2\2\2\u0127\u0128\t\26\2\2\u0128\\\3\2\2\2\u0129\u012a\t\27\2\2\u012a"+
		"^\3\2\2\2\u012b\u012c\t\30\2\2\u012c`\3\2\2\2\u012d\u012e\t\31\2\2\u012e"+
		"b\3\2\2\2\u012f\u0130\t\32\2\2\u0130d\3\2\2\2\u0131\u0132\t\33\2\2\u0132"+
		"f\3\2\2\2\u0133\u0134\t\34\2\2\u0134h\3\2\2\2\u0135\u0136\t\35\2\2\u0136"+
		"j\3\2\2\2\u0137\u0138\t\36\2\2\u0138l\3\2\2\2\u0139\u013a\t\37\2\2\u013a"+
		"n\3\2\2\2\u013b\u013c\t \2\2\u013cp\3\2\2\2\u013d\u013e\t!\2\2\u013er"+
		"\3\2\2\2\u013f\u0140\t\"\2\2\u0140t\3\2\2\2\u0141\u0143\t#\2\2\u0142\u0141"+
		"\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\3\2\2\2\u0146\u0147\b;\2\2\u0147v\3\2\2\2\22\2\u00c2\u00c8\u00cc"+
		"\u00d2\u00d4\u00da\u00df\u00e5\u00e9\u00ec\u00f1\u00f6\u00fb\u0101\u0144"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}