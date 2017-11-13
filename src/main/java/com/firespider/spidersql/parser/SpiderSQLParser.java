// Generated from D:/firespider-spidersql\SpiderSQL.g4 by ANTLR 4.7
package com.firespider.spidersql.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SpiderSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, GET_STR=14, SAVE_STR=15, SCAN_STR=16, 
		DESC_STR=17, PRINT_STR=18, PARALLEL=19, SERIAL=20, ASSIGN=21, SYMBOL=22, 
		C_VAR=23, STRING=24, DOUBLE=25, INT=26, WS=27;
	public static final int
		RULE_spidersql = 0, RULE_multiple_statement = 1, RULE_combine_statement = 2, 
		RULE_simple_statement = 3, RULE_push_statement = 4, RULE_mul_var = 5, 
		RULE_var = 6, RULE_assign_statement = 7, RULE_get = 8, RULE_save = 9, 
		RULE_desc = 10, RULE_scan = 11, RULE_print = 12, RULE_obj = 13, RULE_map = 14, 
		RULE_array = 15, RULE_value = 16, RULE_c_mul_var = 17;
	public static final String[] ruleNames = {
		"spidersql", "multiple_statement", "combine_statement", "simple_statement", 
		"push_statement", "mul_var", "var", "assign_statement", "get", "save", 
		"desc", "scan", "print", "obj", "map", "array", "value", "c_mul_var"
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

	@Override
	public String getGrammarFileName() { return "SpiderSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SpiderSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SpidersqlContext extends ParserRuleContext {
		public SpidersqlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spidersql; }
	 
		public SpidersqlContext() { }
		public void copyFrom(SpidersqlContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExecuteMultipleContext extends SpidersqlContext {
		public Multiple_statementContext multiple_statement() {
			return getRuleContext(Multiple_statementContext.class,0);
		}
		public ExecuteMultipleContext(SpidersqlContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterExecuteMultiple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitExecuteMultiple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitExecuteMultiple(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExecuteSimpleContext extends SpidersqlContext {
		public Combine_statementContext combine_statement() {
			return getRuleContext(Combine_statementContext.class,0);
		}
		public ExecuteSimpleContext(SpidersqlContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterExecuteSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitExecuteSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitExecuteSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpidersqlContext spidersql() throws RecognitionException {
		SpidersqlContext _localctx = new SpidersqlContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_spidersql);
		try {
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new ExecuteMultipleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				multiple_statement();
				}
				break;
			case 2:
				_localctx = new ExecuteSimpleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				combine_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiple_statementContext extends ParserRuleContext {
		public List<Combine_statementContext> combine_statement() {
			return getRuleContexts(Combine_statementContext.class);
		}
		public Combine_statementContext combine_statement(int i) {
			return getRuleContext(Combine_statementContext.class,i);
		}
		public List<TerminalNode> SERIAL() { return getTokens(SpiderSQLParser.SERIAL); }
		public TerminalNode SERIAL(int i) {
			return getToken(SpiderSQLParser.SERIAL, i);
		}
		public Multiple_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterMultiple_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitMultiple_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitMultiple_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_statementContext multiple_statement() throws RecognitionException {
		Multiple_statementContext _localctx = new Multiple_statementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_multiple_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			combine_statement();
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				match(SERIAL);
				setState(42);
				combine_statement();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SERIAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Combine_statementContext extends ParserRuleContext {
		public List<Simple_statementContext> simple_statement() {
			return getRuleContexts(Simple_statementContext.class);
		}
		public Simple_statementContext simple_statement(int i) {
			return getRuleContext(Simple_statementContext.class,i);
		}
		public List<TerminalNode> PARALLEL() { return getTokens(SpiderSQLParser.PARALLEL); }
		public TerminalNode PARALLEL(int i) {
			return getToken(SpiderSQLParser.PARALLEL, i);
		}
		public Combine_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combine_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterCombine_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitCombine_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitCombine_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Combine_statementContext combine_statement() throws RecognitionException {
		Combine_statementContext _localctx = new Combine_statementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_combine_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			simple_statement();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PARALLEL) {
				{
				{
				setState(48);
				match(PARALLEL);
				setState(49);
				simple_statement();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_statementContext extends ParserRuleContext {
		public Assign_statementContext assign_statement() {
			return getRuleContext(Assign_statementContext.class,0);
		}
		public Push_statementContext push_statement() {
			return getRuleContext(Push_statementContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public SaveContext save() {
			return getRuleContext(SaveContext.class,0);
		}
		public Simple_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterSimple_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitSimple_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitSimple_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_statementContext simple_statement() throws RecognitionException {
		Simple_statementContext _localctx = new Simple_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_simple_statement);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				assign_statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				push_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				print();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				save();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Push_statementContext extends ParserRuleContext {
		public Push_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_push_statement; }
	 
		public Push_statementContext() { }
		public void copyFrom(Push_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiplePushContext extends Push_statementContext {
		public List<Mul_varContext> mul_var() {
			return getRuleContexts(Mul_varContext.class);
		}
		public Mul_varContext mul_var(int i) {
			return getRuleContext(Mul_varContext.class,i);
		}
		public TerminalNode INT() { return getToken(SpiderSQLParser.INT, 0); }
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public MultiplePushContext(Push_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterMultiplePush(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitMultiplePush(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitMultiplePush(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefaultPushContext extends Push_statementContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public Mul_varContext mul_var() {
			return getRuleContext(Mul_varContext.class,0);
		}
		public DefaultPushContext(Push_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterDefaultPush(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitDefaultPush(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitDefaultPush(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CountingPushContext extends Push_statementContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public TerminalNode INT() { return getToken(SpiderSQLParser.INT, 0); }
		public Mul_varContext mul_var() {
			return getRuleContext(Mul_varContext.class,0);
		}
		public CountingPushContext(Push_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterCountingPush(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitCountingPush(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitCountingPush(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Push_statementContext push_statement() throws RecognitionException {
		Push_statementContext _localctx = new Push_statementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_push_statement);
		try {
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new DefaultPushContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				var();
				setState(62);
				match(T__0);
				setState(65);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case C_VAR:
					{
					setState(63);
					var();
					}
					break;
				case T__4:
					{
					setState(64);
					mul_var();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				_localctx = new CountingPushContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				var();
				setState(68);
				match(T__1);
				setState(69);
				match(INT);
				setState(70);
				match(T__2);
				setState(73);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case C_VAR:
					{
					setState(71);
					var();
					}
					break;
				case T__4:
					{
					setState(72);
					mul_var();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new MultiplePushContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				mul_var();
				setState(76);
				match(T__1);
				setState(77);
				match(INT);
				setState(78);
				match(T__3);
				setState(79);
				var();
				setState(80);
				match(T__2);
				setState(83);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case C_VAR:
					{
					setState(81);
					var();
					}
					break;
				case T__4:
					{
					setState(82);
					mul_var();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mul_varContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public Mul_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterMul_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitMul_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitMul_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mul_varContext mul_var() throws RecognitionException {
		Mul_varContext _localctx = new Mul_varContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mul_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__4);
			setState(88);
			var();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(89);
				match(T__3);
				setState(90);
				var();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public Assign_statementContext assign_statement() {
			return getRuleContext(Assign_statementContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_var);
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(C_VAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				assign_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_statementContext extends ParserRuleContext {
		public Assign_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_statement; }
	 
		public Assign_statementContext() { }
		public void copyFrom(Assign_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignSaveContext extends Assign_statementContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(SpiderSQLParser.ASSIGN, 0); }
		public SaveContext save() {
			return getRuleContext(SaveContext.class,0);
		}
		public AssignSaveContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterAssignSave(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitAssignSave(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitAssignSave(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignScanContext extends Assign_statementContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(SpiderSQLParser.ASSIGN, 0); }
		public ScanContext scan() {
			return getRuleContext(ScanContext.class,0);
		}
		public AssignScanContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterAssignScan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitAssignScan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitAssignScan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignDescContext extends Assign_statementContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(SpiderSQLParser.ASSIGN, 0); }
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public AssignDescContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterAssignDesc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitAssignDesc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitAssignDesc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignGetContext extends Assign_statementContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(SpiderSQLParser.ASSIGN, 0); }
		public GetContext get() {
			return getRuleContext(GetContext.class,0);
		}
		public AssignGetContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterAssignGet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitAssignGet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitAssignGet(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignValueContext extends Assign_statementContext {
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(SpiderSQLParser.ASSIGN, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public AssignValueContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterAssignValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitAssignValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitAssignValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_statementContext assign_statement() throws RecognitionException {
		Assign_statementContext _localctx = new Assign_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assign_statement);
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new AssignValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(C_VAR);
				setState(103);
				match(ASSIGN);
				setState(104);
				value(0);
				}
				break;
			case 2:
				_localctx = new AssignGetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(C_VAR);
				setState(106);
				match(ASSIGN);
				setState(107);
				get();
				}
				break;
			case 3:
				_localctx = new AssignSaveContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(C_VAR);
				setState(109);
				match(ASSIGN);
				setState(110);
				save();
				}
				break;
			case 4:
				_localctx = new AssignScanContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(C_VAR);
				setState(112);
				match(ASSIGN);
				setState(113);
				scan();
				}
				break;
			case 5:
				_localctx = new AssignDescContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				match(C_VAR);
				setState(115);
				match(ASSIGN);
				setState(116);
				desc();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GetContext extends ParserRuleContext {
		public TerminalNode GET_STR() { return getToken(SpiderSQLParser.GET_STR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public GetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterGet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitGet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitGet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetContext get() throws RecognitionException {
		GetContext _localctx = new GetContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_get);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(GET_STR);
			setState(120);
			obj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SaveContext extends ParserRuleContext {
		public TerminalNode SAVE_STR() { return getToken(SpiderSQLParser.SAVE_STR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public SaveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_save; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterSave(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitSave(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitSave(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SaveContext save() throws RecognitionException {
		SaveContext _localctx = new SaveContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_save);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(SAVE_STR);
			setState(123);
			obj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescContext extends ParserRuleContext {
		public TerminalNode DESC_STR() { return getToken(SpiderSQLParser.DESC_STR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public DescContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_desc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterDesc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitDesc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitDesc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescContext desc() throws RecognitionException {
		DescContext _localctx = new DescContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_desc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(DESC_STR);
			setState(126);
			obj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScanContext extends ParserRuleContext {
		public TerminalNode SCAN_STR() { return getToken(SpiderSQLParser.SCAN_STR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ScanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterScan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitScan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitScan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScanContext scan() throws RecognitionException {
		ScanContext _localctx = new ScanContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_scan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(SCAN_STR);
			setState(129);
			obj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public TerminalNode PRINT_STR() { return getToken(SpiderSQLParser.PRINT_STR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public C_mul_varContext c_mul_var() {
			return getRuleContext(C_mul_varContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(PRINT_STR);
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(132);
				obj();
				}
				break;
			case 2:
				{
				setState(133);
				match(C_VAR);
				}
				break;
			case 3:
				{
				setState(134);
				c_mul_var();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjContext extends ParserRuleContext {
		public List<MapContext> map() {
			return getRuleContexts(MapContext.class);
		}
		public MapContext map(int i) {
			return getRuleContext(MapContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitObj(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitObj(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_obj);
		int _la;
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(T__6);
				setState(138);
				map();
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(139);
					match(T__3);
					setState(140);
					map();
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(T__7);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(T__6);
				setState(149);
				match(T__7);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public TerminalNode STRING() { return getToken(SpiderSQLParser.STRING, 0); }
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitMap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !(_la==C_VAR || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(153);
			match(ASSIGN);
			setState(154);
			value(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_array);
		int _la;
		try {
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(T__8);
				setState(157);
				value(0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(158);
					match(T__3);
					setState(159);
					value(0);
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				match(T__8);
				setState(168);
				match(T__9);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(SpiderSQLParser.STRING, 0); }
		public TerminalNode INT() { return getToken(SpiderSQLParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(SpiderSQLParser.DOUBLE, 0); }
		public TerminalNode C_VAR() { return getToken(SpiderSQLParser.C_VAR, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> SYMBOL() { return getTokens(SpiderSQLParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(SpiderSQLParser.SYMBOL, i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		return value(0);
	}

	private ValueContext value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValueContext _localctx = new ValueContext(_ctx, _parentState);
		ValueContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(172);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(173);
				match(INT);
				}
				break;
			case DOUBLE:
				{
				setState(174);
				match(DOUBLE);
				}
				break;
			case C_VAR:
				{
				setState(175);
				match(C_VAR);
				}
				break;
			case T__6:
				{
				setState(176);
				obj();
				}
				break;
			case T__8:
				{
				setState(177);
				array();
				}
				break;
			case T__10:
				{
				setState(178);
				match(T__10);
				}
				break;
			case T__11:
				{
				setState(179);
				match(T__11);
				}
				break;
			case T__12:
				{
				setState(180);
				match(T__12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ValueContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_value);
					setState(183);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(186); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(184);
							match(SYMBOL);
							setState(185);
							value(0);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(188); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class C_mul_varContext extends ParserRuleContext {
		public List<TerminalNode> C_VAR() { return getTokens(SpiderSQLParser.C_VAR); }
		public TerminalNode C_VAR(int i) {
			return getToken(SpiderSQLParser.C_VAR, i);
		}
		public C_mul_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c_mul_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).enterC_mul_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpiderSQLListener ) ((SpiderSQLListener)listener).exitC_mul_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpiderSQLVisitor ) return ((SpiderSQLVisitor<? extends T>)visitor).visitC_mul_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C_mul_varContext c_mul_var() throws RecognitionException {
		C_mul_varContext _localctx = new C_mul_varContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_c_mul_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(195);
				match(C_VAR);
				}
				}
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==C_VAR );
			setState(202); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(200);
				match(T__3);
				setState(201);
				match(C_VAR);
				}
				}
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 16:
			return value_sempred((ValueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean value_sempred(ValueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00d1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\5\2)\n\2\3\3\3\3\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\3\4"+
		"\7\4\65\n\4\f\4\16\48\13\4\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\5"+
		"\6D\n\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6L\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6V\n\6\5\6X\n\6\3\7\3\7\3\7\3\7\7\7^\n\7\f\7\16\7a\13\7\3\7\3\7\3"+
		"\b\3\b\5\bg\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\tx\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\5\16\u008a\n\16\3\17\3\17\3\17\3\17\7\17\u0090\n\17\f"+
		"\17\16\17\u0093\13\17\3\17\3\17\3\17\3\17\5\17\u0099\n\17\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\7\21\u00a3\n\21\f\21\16\21\u00a6\13\21\3"+
		"\21\3\21\3\21\3\21\5\21\u00ac\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u00b8\n\22\3\22\3\22\3\22\6\22\u00bd\n\22\r\22\16"+
		"\22\u00be\7\22\u00c1\n\22\f\22\16\22\u00c4\13\22\3\23\6\23\u00c7\n\23"+
		"\r\23\16\23\u00c8\3\23\3\23\6\23\u00cd\n\23\r\23\16\23\u00ce\3\23\2\3"+
		"\"\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\3\3\2\31\32\2\u00e1"+
		"\2(\3\2\2\2\4*\3\2\2\2\6\61\3\2\2\2\b=\3\2\2\2\nW\3\2\2\2\fY\3\2\2\2\16"+
		"f\3\2\2\2\20w\3\2\2\2\22y\3\2\2\2\24|\3\2\2\2\26\177\3\2\2\2\30\u0082"+
		"\3\2\2\2\32\u0085\3\2\2\2\34\u0098\3\2\2\2\36\u009a\3\2\2\2 \u00ab\3\2"+
		"\2\2\"\u00b7\3\2\2\2$\u00c6\3\2\2\2&)\5\4\3\2\')\5\6\4\2(&\3\2\2\2(\'"+
		"\3\2\2\2)\3\3\2\2\2*-\5\6\4\2+,\7\26\2\2,.\5\6\4\2-+\3\2\2\2./\3\2\2\2"+
		"/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61\66\5\b\5\2\62\63\7\25\2\2\63\65"+
		"\5\b\5\2\64\62\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\7\3"+
		"\2\2\28\66\3\2\2\29>\5\20\t\2:>\5\n\6\2;>\5\32\16\2<>\5\24\13\2=9\3\2"+
		"\2\2=:\3\2\2\2=;\3\2\2\2=<\3\2\2\2>\t\3\2\2\2?@\5\16\b\2@C\7\3\2\2AD\5"+
		"\16\b\2BD\5\f\7\2CA\3\2\2\2CB\3\2\2\2DX\3\2\2\2EF\5\16\b\2FG\7\4\2\2G"+
		"H\7\34\2\2HK\7\5\2\2IL\5\16\b\2JL\5\f\7\2KI\3\2\2\2KJ\3\2\2\2LX\3\2\2"+
		"\2MN\5\f\7\2NO\7\4\2\2OP\7\34\2\2PQ\7\6\2\2QR\5\16\b\2RU\7\5\2\2SV\5\16"+
		"\b\2TV\5\f\7\2US\3\2\2\2UT\3\2\2\2VX\3\2\2\2W?\3\2\2\2WE\3\2\2\2WM\3\2"+
		"\2\2X\13\3\2\2\2YZ\7\7\2\2Z_\5\16\b\2[\\\7\6\2\2\\^\5\16\b\2][\3\2\2\2"+
		"^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7\b\2\2c\r\3\2\2"+
		"\2dg\7\31\2\2eg\5\20\t\2fd\3\2\2\2fe\3\2\2\2g\17\3\2\2\2hi\7\31\2\2ij"+
		"\7\27\2\2jx\5\"\22\2kl\7\31\2\2lm\7\27\2\2mx\5\22\n\2no\7\31\2\2op\7\27"+
		"\2\2px\5\24\13\2qr\7\31\2\2rs\7\27\2\2sx\5\30\r\2tu\7\31\2\2uv\7\27\2"+
		"\2vx\5\26\f\2wh\3\2\2\2wk\3\2\2\2wn\3\2\2\2wq\3\2\2\2wt\3\2\2\2x\21\3"+
		"\2\2\2yz\7\20\2\2z{\5\34\17\2{\23\3\2\2\2|}\7\21\2\2}~\5\34\17\2~\25\3"+
		"\2\2\2\177\u0080\7\23\2\2\u0080\u0081\5\34\17\2\u0081\27\3\2\2\2\u0082"+
		"\u0083\7\22\2\2\u0083\u0084\5\34\17\2\u0084\31\3\2\2\2\u0085\u0089\7\24"+
		"\2\2\u0086\u008a\5\34\17\2\u0087\u008a\7\31\2\2\u0088\u008a\5$\23\2\u0089"+
		"\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\33\3\2\2"+
		"\2\u008b\u008c\7\t\2\2\u008c\u0091\5\36\20\2\u008d\u008e\7\6\2\2\u008e"+
		"\u0090\5\36\20\2\u008f\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0095\7\n\2\2\u0095\u0099\3\2\2\2\u0096\u0097\7\t\2\2\u0097\u0099\7\n"+
		"\2\2\u0098\u008b\3\2\2\2\u0098\u0096\3\2\2\2\u0099\35\3\2\2\2\u009a\u009b"+
		"\t\2\2\2\u009b\u009c\7\27\2\2\u009c\u009d\5\"\22\2\u009d\37\3\2\2\2\u009e"+
		"\u009f\7\13\2\2\u009f\u00a4\5\"\22\2\u00a0\u00a1\7\6\2\2\u00a1\u00a3\5"+
		"\"\22\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7\f"+
		"\2\2\u00a8\u00ac\3\2\2\2\u00a9\u00aa\7\13\2\2\u00aa\u00ac\7\f\2\2\u00ab"+
		"\u009e\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac!\3\2\2\2\u00ad\u00ae\b\22\1\2"+
		"\u00ae\u00b8\7\32\2\2\u00af\u00b8\7\34\2\2\u00b0\u00b8\7\33\2\2\u00b1"+
		"\u00b8\7\31\2\2\u00b2\u00b8\5\34\17\2\u00b3\u00b8\5 \21\2\u00b4\u00b8"+
		"\7\r\2\2\u00b5\u00b8\7\16\2\2\u00b6\u00b8\7\17\2\2\u00b7\u00ad\3\2\2\2"+
		"\u00b7\u00af\3\2\2\2\u00b7\u00b0\3\2\2\2\u00b7\u00b1\3\2\2\2\u00b7\u00b2"+
		"\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b7\u00b4\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b6\3\2\2\2\u00b8\u00c2\3\2\2\2\u00b9\u00bc\f\b\2\2\u00ba\u00bb\7\30"+
		"\2\2\u00bb\u00bd\5\"\22\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00b9\3\2"+
		"\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"#\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c7\7\31\2\2\u00c6\u00c5\3\2\2\2"+
		"\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cc"+
		"\3\2\2\2\u00ca\u00cb\7\6\2\2\u00cb\u00cd\7\31\2\2\u00cc\u00ca\3\2\2\2"+
		"\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf%\3"+
		"\2\2\2\27(/\66=CKUW_fw\u0089\u0091\u0098\u00a4\u00ab\u00b7\u00be\u00c2"+
		"\u00c8\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}