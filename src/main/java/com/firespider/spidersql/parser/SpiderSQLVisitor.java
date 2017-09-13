// Generated from D:/firespider-spidersql\SpiderSQL.g4 by ANTLR 4.7
package com.firespider.spidersql.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SpiderSQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SpiderSQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code executeMultiple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecuteMultiple(SpiderSQLParser.ExecuteMultipleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code executeSimple}
	 * labeled alternative in {@link SpiderSQLParser#spidersql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecuteSimple(SpiderSQLParser.ExecuteSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#multiple_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_statement(SpiderSQLParser.Multiple_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#combine_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombine_statement(SpiderSQLParser.Combine_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#simple_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_statement(SpiderSQLParser.Simple_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultPush(SpiderSQLParser.DefaultPushContext ctx);
	/**
	 * Visit a parse tree produced by the {@code countingPush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCountingPush(SpiderSQLParser.CountingPushContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplePush}
	 * labeled alternative in {@link SpiderSQLParser#push_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplePush(SpiderSQLParser.MultiplePushContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#mul_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul_var(SpiderSQLParser.Mul_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(SpiderSQLParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignValue}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignValue(SpiderSQLParser.AssignValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignGet}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignGet(SpiderSQLParser.AssignGetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignSave}
	 * labeled alternative in {@link SpiderSQLParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignSave(SpiderSQLParser.AssignSaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#get}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGet(SpiderSQLParser.GetContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#save}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSave(SpiderSQLParser.SaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#desc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesc(SpiderSQLParser.DescContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#scan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScan(SpiderSQLParser.ScanContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SpiderSQLParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#obj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObj(SpiderSQLParser.ObjContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(SpiderSQLParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(SpiderSQLParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SpiderSQLParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpiderSQLParser#c_mul_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_mul_var(SpiderSQLParser.C_mul_varContext ctx);
}