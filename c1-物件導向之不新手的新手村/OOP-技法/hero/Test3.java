package hero;

import java.util.List;

/**
 * @author johnny@waterballsa.tw
 */
public class Test3 {
    public static void main(String[] args) {
        Hero A = new Hero();
        Hero B = new Hero();
        Hero C = new Hero();
        Guild guild = new Guild("水球軟體學院", List.of(A));
        System.out.printf("初始成員數量：%d\n", guild.getMembers().size());

        guild.join(B);
        System.out.printf("B 加入, 成員數量：%d\n", guild.getMembers().size());
        guild.join(C);
        System.out.printf("C 加入, 成員數量：%d\n", guild.getMembers().size());

        try {
            guild.join(A);
        } catch (Exception err) {
            System.out.println("A 成員不能重複加入");
        }

        guild.leave(A);
        System.out.printf("A 離去, 成員數量：%d\n", guild.getMembers().size());
        guild.leave(B);
        System.out.printf("B 離去, 成員數量：%d\n", guild.getMembers().size());

        try {
            guild.leave(C);
        } catch (Exception err) {
            System.out.println("唯一的一位公會成員 (C) 無法申請離開");
        }

        try {
            guild.leave(A);
        } catch (Exception err) {
            System.out.println("只有公會成員才能申請離開, A 不是成員，無法離開");
        }

    }
}
