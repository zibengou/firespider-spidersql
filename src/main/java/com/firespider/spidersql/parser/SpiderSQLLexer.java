// Generated from D:/firespider-spidersql\SpiderSQL.g4 by ANTLR 4.7
package com.firespider.spidersql.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SpiderSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, GET_STR=14, SAVE_STR=15, SCAN_STR=16, 
		DESC_STR=17, PRINT_STR=18, PARALLEL=19, SERIAL=20, ASSIGN=21, C_VAR=22, 
		STRING=23, NUMBER=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "GET_STR", "SAVE_STR", "SCAN_STR", 
		"DESC_STR", "PRINT_STR", "PARALLEL", "SERIAL", "ASSIGN", "C_VAR", "STRING", 
		"NUMBER", "ID", "ESC", "UNICODE", "HEX", "DIGIT", "A", "B", "C", "D", 
		"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
		"S", "T", "U", "V", "W", "X", "Y", "Z", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'-['", "']>'", "','", "'('", "')'", "'{'", "'}'", "'['", 
		"']'", "'true'", "'false'", "'null'", null, null, null, null, null, "';'", 
		"'|'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "GET_STR", "SAVE_STR", "SCAN_STR", "DESC_STR", "PRINT_STR", 
		"PARALLEL", "SERIAL", "ASSIGN", "C_VAR", "STRING", "NUMBER", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u013b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\6\27\u00bb\n\27\r\27\16\27\u00bc\3\27\3\27\6\27"+
		"\u00c1\n\27\r\27\16\27\u00c2\7\27\u00c5\n\27\f\27\16\27\u00c8\13\27\3"+
		"\30\3\30\3\30\7\30\u00cd\n\30\f\30\16\30\u00d0\13\30\3\30\3\30\3\31\5"+
		"\31\u00d5\n\31\3\31\6\31\u00d8\n\31\r\31\16\31\u00d9\3\31\3\31\6\31\u00de"+
		"\n\31\r\31\16\31\u00df\7\31\u00e2\n\31\f\31\16\31\u00e5\13\31\3\32\6\32"+
		"\u00e8\n\32\r\32\16\32\u00e9\3\32\7\32\u00ed\n\32\f\32\16\32\u00f0\13"+
		"\32\3\33\3\33\3\33\5\33\u00f5\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\3"+
		"8\39\69\u0136\n9\r9\169\u0137\39\39\2\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S"+
		"\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\33\3\2#\4\2$$^^\4\2C\\c"+
		"|\5\2\62;C\\c|\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\3\2\62;\4\2CCcc\4\2"+
		"DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4"+
		"\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUu"+
		"u\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\f\17"+
		"\17\"\"\2\u0128\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5v\3\2\2\2\7y\3\2\2"+
		"\2\t|\3\2\2\2\13~\3\2\2\2\r\u0080\3\2\2\2\17\u0082\3\2\2\2\21\u0084\3"+
		"\2\2\2\23\u0086\3\2\2\2\25\u0088\3\2\2\2\27\u008a\3\2\2\2\31\u008f\3\2"+
		"\2\2\33\u0095\3\2\2\2\35\u009a\3\2\2\2\37\u009e\3\2\2\2!\u00a3\3\2\2\2"+
		"#\u00a8\3\2\2\2%\u00ad\3\2\2\2\'\u00b3\3\2\2\2)\u00b5\3\2\2\2+\u00b7\3"+
		"\2\2\2-\u00ba\3\2\2\2/\u00c9\3\2\2\2\61\u00d4\3\2\2\2\63\u00e7\3\2\2\2"+
		"\65\u00f1\3\2\2\2\67\u00f6\3\2\2\29\u00fc\3\2\2\2;\u00fe\3\2\2\2=\u0100"+
		"\3\2\2\2?\u0102\3\2\2\2A\u0104\3\2\2\2C\u0106\3\2\2\2E\u0108\3\2\2\2G"+
		"\u010a\3\2\2\2I\u010c\3\2\2\2K\u010e\3\2\2\2M\u0110\3\2\2\2O\u0112\3\2"+
		"\2\2Q\u0114\3\2\2\2S\u0116\3\2\2\2U\u0118\3\2\2\2W\u011a\3\2\2\2Y\u011c"+
		"\3\2\2\2[\u011e\3\2\2\2]\u0120\3\2\2\2_\u0122\3\2\2\2a\u0124\3\2\2\2c"+
		"\u0126\3\2\2\2e\u0128\3\2\2\2g\u012a\3\2\2\2i\u012c\3\2\2\2k\u012e\3\2"+
		"\2\2m\u0130\3\2\2\2o\u0132\3\2\2\2q\u0135\3\2\2\2st\7/\2\2tu\7@\2\2u\4"+
		"\3\2\2\2vw\7/\2\2wx\7]\2\2x\6\3\2\2\2yz\7_\2\2z{\7@\2\2{\b\3\2\2\2|}\7"+
		".\2\2}\n\3\2\2\2~\177\7*\2\2\177\f\3\2\2\2\u0080\u0081\7+\2\2\u0081\16"+
		"\3\2\2\2\u0082\u0083\7}\2\2\u0083\20\3\2\2\2\u0084\u0085\7\177\2\2\u0085"+
		"\22\3\2\2\2\u0086\u0087\7]\2\2\u0087\24\3\2\2\2\u0088\u0089\7_\2\2\u0089"+
		"\26\3\2\2\2\u008a\u008b\7v\2\2\u008b\u008c\7t\2\2\u008c\u008d\7w\2\2\u008d"+
		"\u008e\7g\2\2\u008e\30\3\2\2\2\u008f\u0090\7h\2\2\u0090\u0091\7c\2\2\u0091"+
		"\u0092\7n\2\2\u0092\u0093\7u\2\2\u0093\u0094\7g\2\2\u0094\32\3\2\2\2\u0095"+
		"\u0096\7p\2\2\u0096\u0097\7w\2\2\u0097\u0098\7n\2\2\u0098\u0099\7n\2\2"+
		"\u0099\34\3\2\2\2\u009a\u009b\5I%\2\u009b\u009c\5E#\2\u009c\u009d\5c\62"+
		"\2\u009d\36\3\2\2\2\u009e\u009f\5a\61\2\u009f\u00a0\5=\37\2\u00a0\u00a1"+
		"\5g\64\2\u00a1\u00a2\5E#\2\u00a2 \3\2\2\2\u00a3\u00a4\5a\61\2\u00a4\u00a5"+
		"\5A!\2\u00a5\u00a6\5=\37\2\u00a6\u00a7\5W,\2\u00a7\"\3\2\2\2\u00a8\u00a9"+
		"\5C\"\2\u00a9\u00aa\5E#\2\u00aa\u00ab\5a\61\2\u00ab\u00ac\5A!\2\u00ac"+
		"$\3\2\2\2\u00ad\u00ae\5[.\2\u00ae\u00af\5_\60\2\u00af\u00b0\5M\'\2\u00b0"+
		"\u00b1\5W,\2\u00b1\u00b2\5c\62\2\u00b2&\3\2\2\2\u00b3\u00b4\7=\2\2\u00b4"+
		"(\3\2\2\2\u00b5\u00b6\7~\2\2\u00b6*\3\2\2\2\u00b7\u00b8\7<\2\2\u00b8,"+
		"\3\2\2\2\u00b9\u00bb\5\63\32\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2"+
		"\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00c6\3\2\2\2\u00be\u00c0"+
		"\7\60\2\2\u00bf\u00c1\5\63\32\2\u00c0\u00bf\3\2\2\2\u00c1\u00c2\3\2\2"+
		"\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00be"+
		"\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		".\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ce\7$\2\2\u00ca\u00cd\5\65\33\2"+
		"\u00cb\u00cd\n\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0"+
		"\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d2\7$\2\2\u00d2\60\3\2\2\2\u00d3\u00d5\7/\2\2"+
		"\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d8"+
		"\5;\36\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00e3\3\2\2\2\u00db\u00dd\7\60\2\2\u00dc\u00de\5"+
		";\36\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00db\3\2\2\2\u00e2\u00e5\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\62\3\2\2\2\u00e5\u00e3"+
		"\3\2\2\2\u00e6\u00e8\t\3\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ee\3\2\2\2\u00eb\u00ed\t\4"+
		"\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\64\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f4\7^\2\2"+
		"\u00f2\u00f5\t\5\2\2\u00f3\u00f5\5\67\34\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3"+
		"\3\2\2\2\u00f5\66\3\2\2\2\u00f6\u00f7\7w\2\2\u00f7\u00f8\59\35\2\u00f8"+
		"\u00f9\59\35\2\u00f9\u00fa\59\35\2\u00fa\u00fb\59\35\2\u00fb8\3\2\2\2"+
		"\u00fc\u00fd\t\6\2\2\u00fd:\3\2\2\2\u00fe\u00ff\t\7\2\2\u00ff<\3\2\2\2"+
		"\u0100\u0101\t\b\2\2\u0101>\3\2\2\2\u0102\u0103\t\t\2\2\u0103@\3\2\2\2"+
		"\u0104\u0105\t\n\2\2\u0105B\3\2\2\2\u0106\u0107\t\13\2\2\u0107D\3\2\2"+
		"\2\u0108\u0109\t\f\2\2\u0109F\3\2\2\2\u010a\u010b\t\r\2\2\u010bH\3\2\2"+
		"\2\u010c\u010d\t\16\2\2\u010dJ\3\2\2\2\u010e\u010f\t\17\2\2\u010fL\3\2"+
		"\2\2\u0110\u0111\t\20\2\2\u0111N\3\2\2\2\u0112\u0113\t\21\2\2\u0113P\3"+
		"\2\2\2\u0114\u0115\t\22\2\2\u0115R\3\2\2\2\u0116\u0117\t\23\2\2\u0117"+
		"T\3\2\2\2\u0118\u0119\t\24\2\2\u0119V\3\2\2\2\u011a\u011b\t\25\2\2\u011b"+
		"X\3\2\2\2\u011c\u011d\t\26\2\2\u011dZ\3\2\2\2\u011e\u011f\t\27\2\2\u011f"+
		"\\\3\2\2\2\u0120\u0121\t\30\2\2\u0121^\3\2\2\2\u0122\u0123\t\31\2\2\u0123"+
		"`\3\2\2\2\u0124\u0125\t\32\2\2\u0125b\3\2\2\2\u0126\u0127\t\33\2\2\u0127"+
		"d\3\2\2\2\u0128\u0129\t\34\2\2\u0129f\3\2\2\2\u012a\u012b\t\35\2\2\u012b"+
		"h\3\2\2\2\u012c\u012d\t\36\2\2\u012dj\3\2\2\2\u012e\u012f\t\37\2\2\u012f"+
		"l\3\2\2\2\u0130\u0131\t \2\2\u0131n\3\2\2\2\u0132\u0133\t!\2\2\u0133p"+
		"\3\2\2\2\u0134\u0136\t\"\2\2\u0135\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\b9"+
		"\2\2\u013ar\3\2\2\2\20\2\u00bc\u00c2\u00c6\u00cc\u00ce\u00d4\u00d9\u00df"+
		"\u00e3\u00e9\u00ee\u00f4\u0137\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}