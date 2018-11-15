package top.yeonon.yim.protocol.command;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yeonon
 * @date 2018/11/15 0015 20:51
 **/
public class CommandExecutorManager implements CommandExecutor {

    private static final Map<String, CommandExecutor> commandExecutorMaps;

    public static final CommandExecutorManager INSTANCE;

    static {
        commandExecutorMaps = new ConcurrentHashMap<>();
        commandExecutorMaps.put(Command.LOGOUT_REQUEST.getName(), LogoutCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.SINGLE_MESSAGE_REQUEST.getName(), SingleMessageCommandExecutor.INSTANCE);


        INSTANCE = new CommandExecutorManager();
    }

    private CommandExecutorManager() {}



    @Override
    public void exec(Scanner scanner, Channel channel) {

        //这里非常重要，防止线程再次被阻塞，从而无法判断循环条件
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        System.out.print("请输入指令：");
        String command = scanner.next();

        CommandExecutor executor = commandExecutorMaps.get(command);
        if (executor == null) {
            System.err.println("不存在该指令！");
            return;
        }
        executor.exec(scanner, channel);
    }
}
