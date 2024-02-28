package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {
    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @Command(name = "add", description = "增加，混合标准选项")
    static class AddCommand implements Runnable {
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @Command(name = "delete", description = "删除，混合标准选项")
    static class DeleteCommand implements Runnable {
        public void run() {
            System.out.println("执行删除命令");
        }
    }

    @Command(name = "query", description = "查询，混合标准选项")
    static class QueryCommand implements Runnable {
        public void run() {
            System.out.println("执行查询命令");
        }
    }

    public static void main(String[] args) {
        // 执行主命令
//        String[] myArgs = new String[]{};
        // 查看主命令的帮助手册
        String [] myArgs = new String[]{"--help"};
        // 执行增加命令
//        String [] myArgs = new String[]{"add"};
        // 执行增加命令的帮助手册
//        String [] myArgs = new String[]{"add", "--help"};
        // 执行不存在的命令命令
//        String [] myArgs = new String[]{"update"};
        int exitCode = new CommandLine(new SubCommandExample())
            .addSubcommand(new AddCommand())
            .addSubcommand(new DeleteCommand())
            .addSubcommand(new QueryCommand())
            .execute(myArgs);

//        System.out.println(exitCode);
    }

}
