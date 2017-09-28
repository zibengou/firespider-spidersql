// Generated from D:/firespider-spidersql\SpiderSQL.g4 by ANTLR 4.7
package com.firespider.spidersql.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SpiderSQLParser}.
 */
public interface SpiderSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code executeMultiple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 */
	void enterExecuteMultiple(SpiderSQLParser.ExecuteMultipleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code executeMultiple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 */
	void exitExecuteMultiple(SpiderSQLParser.ExecuteMultipleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code executeSimple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 */
	void enterExecuteSimple(SpiderSQLParser.ExecuteSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code executeSimple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 */
	void exitExecuteSimple(SpiderSQLParser.ExecuteSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#multiple_statement}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_statement(SpiderSQLParser.Multiple_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#multiple_statement}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_statement(SpiderSQLParser.Multiple_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#combine_statement}.
	 * @param ctx the parse tree
	 */
	void enterCombine_statement(SpiderSQLParser.Combine_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#combine_statement}.
	 * @param ctx the parse tree
	 */
	void exitCombine_statement(SpiderSQLParser.Combine_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#simple_statement}.
	 * @param ctx the parse tree
	 */
	void enterSimple_statement(SpiderSQLParser.Simple_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#simple_statement}.
	 * @param ctx the parse tree
	 */
	void exitSimple_statement(SpiderSQLParser.Simple_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultPush(SpiderSQLParser.DefaultPushContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultPush(SpiderSQLParser.DefaultPushContext ctx);
	/**
	 * Enter a parse tree produced by the {@code countingPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void enterCountingPush(SpiderSQLParser.CountingPushContext ctx);
	/**
	 * Exit a parse tree produced by the {@code countingPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void exitCountingPush(SpiderSQLParser.CountingPushContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplePush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void enterMultiplePush(SpiderSQLParser.MultiplePushContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplePush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 */
	void exitMultiplePush(SpiderSQLParser.MultiplePushContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#mul_var}.
	 * @param ctx the parse tree
	 */
	void enterMul_var(SpiderSQLParser.Mul_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#mul_var}.
	 * @param ctx the parse tree
	 */
	void exitMul_var(SpiderSQLParser.Mul_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(SpiderSQLParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(SpiderSQLParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignValue}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignValue(SpiderSQLParser.AssignValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignValue}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignValue(SpiderSQLParser.AssignValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignGet}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignGet(SpiderSQLParser.AssignGetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignGet}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignGet(SpiderSQLParser.AssignGetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignSave}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignSave(SpiderSQLParser.AssignSaveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignSave}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignSave(SpiderSQLParser.AssignSaveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignScan}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignScan(SpiderSQLParser.AssignScanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignScan}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignScan(SpiderSQLParser.AssignScanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignDesc}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignDesc(SpiderSQLParser.AssignDescContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignDesc}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignDesc(SpiderSQLParser.AssignDescContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#get}.
	 * @param ctx the parse tree
	 */
	void enterGet(SpiderSQLParser.GetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#get}.
	 * @param ctx the parse tree
	 */
	void exitGet(SpiderSQLParser.GetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#save}.
	 * @param ctx the parse tree
	 */
	void enterSave(SpiderSQLParser.SaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#save}.
	 * @param ctx the parse tree
	 */
	void exitSave(SpiderSQLParser.SaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#desc}.
	 * @param ctx the parse tree
	 */
	void enterDesc(SpiderSQLParser.DescContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#desc}.
	 * @param ctx the parse tree
	 */
	void exitDesc(SpiderSQLParser.DescContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#scan}.
	 * @param ctx the parse tree
	 */
	void enterScan(SpiderSQLParser.ScanContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#scan}.
	 * @param ctx the parse tree
	 */
	void exitScan(SpiderSQLParser.ScanContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(SpiderSQLParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(SpiderSQLParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(SpiderSQLParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(SpiderSQLParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(SpiderSQLParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(SpiderSQLParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(SpiderSQLParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(SpiderSQLParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SpiderSQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SpiderSQLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpiderSQLParser#c_mul_var}.
	 * @param ctx the parse tree
	 */
	void enterC_mul_var(SpiderSQLParser.C_mul_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpiderSQLParser#c_mul_var}.
	 * @param ctx the parse tree
	 */
	void exitC_mul_var(SpiderSQLParser.C_mul_varContext ctx);
}