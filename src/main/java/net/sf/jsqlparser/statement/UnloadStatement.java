/*
 * Copyright 2023 VMware, Inc. All rights reserved.
 */

package net.sf.jsqlparser.statement;

import java.util.List;

import net.sf.jsqlparser.statement.select.Select;


/**
 * Unload class.
 */
public class UnloadStatement implements Statement {
    private Select selectBody;

    private List<String> parameters;

    public UnloadStatement(Select selectBody, List<String> parameters) {
        this.selectBody = selectBody;
        this.parameters = parameters;
    }

    public Select getSelectBody() {
        return selectBody;
    }

    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public StringBuilder appendTo(StringBuilder builder) {
        builder.append("UNLOAD ( ");
        if (this.selectBody != null) {
            this.selectBody.appendTo(builder);
        }
        builder.append(" ) ");
        appendParameters(builder, parameters);
        return builder;
    }

    @Override
    public String toString() {
        return appendTo(new StringBuilder()).toString();
    }

    public static void appendParameters(StringBuilder builder, List<String> parameters) {
        for (String s : parameters) {
            builder.append(" ").append(s);
        }
    }
}
