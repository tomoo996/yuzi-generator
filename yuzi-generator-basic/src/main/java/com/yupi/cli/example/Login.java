package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {

    @Option(names = {"-u", "--user"}, description = "Username")
    String user;

    @Option(names = {"-p", "--password"}, description = "Password", interactive = true, prompt = "请输入密码:", arity = "0..1")
    String password;

    @Option(names = {"-cp", "--check-password"}, description = "Check password", interactive = true, arity = "0..1")
    String chenkPassword;

    public Integer call() throws Exception {
        System.out.println("password:" + password);
        System.out.println("chenkPassword:" + chenkPassword);
        return 0;
    }

    public static void main(String[] args) {

        new CommandLine(new Login()).execute("-u", "123");
    }
}