package top.yeonon.yim.command;

import io.netty.channel.Channel;
import org.omg.CORBA.COMM_FAILURE;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 命令执行器管理
 * @Author yeonon
 * @date 2018/11/15 0015 20:51
 **/
public class CommandExecutorManager implements CommandExecutor {

    //使用一个Map来保存命令名称 -> 命令执行器实例 的映射
    private static final Map<String, CommandExecutor> commandExecutorMaps;

    //单例
    public static final CommandExecutorManager INSTANCE;


    static {
        //初始化Map
        commandExecutorMaps = new ConcurrentHashMap<>();
        commandExecutorMaps.put(Command.LOGOUT_REQUEST.getName(), LogoutCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.SINGLE_MESSAGE_REQUEST.getName(), SingleMessageCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.CREATE_GROUP_REQUEST.getName(), CreateGroupCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.LIST_GROUP_MEMBER_REQUEST.getName(), ListGroupMemberCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.JOIN_GROUP_REQUEST.getName(), JoinGroupCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.QUITE_GROUP_REQUEST.getName(), QuiteGroupCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.GROUP_MESSAGE_REQUEST.getName(), GroupMessageCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.ADD_FRIEND_REQUEST.getName(), AddFriendCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.DELETE_FRIEND_REQUEST.getName(), DeleteFriendCommandExecutor.INSTANCE);
        commandExecutorMaps.put(Command.LIST_FRIENDS_REQUEST.getName(), ListFriendsCommandExecutor.INSTANCE);

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

        //根据命令取出执行器实例
        CommandExecutor executor = commandExecutorMaps.get(command);

        //如果执行器为null，表示不存在指令
        if (executor == null) {
            System.err.println("不存在该指令！");
            return;
        }

        //执行对应执行器的逻辑
        executor.exec(scanner, channel);
    }
}
