import java.io.File;
import java.util.Scanner;

public class dollar_program {
	if(args.length > 0) {
        File file = new File(args[0]);
        Scanner input = new Scanner(file);
		int tasknum = Integer.parseInt(input.next());
		task[] tasks = new task[tasknum];
		for (int w=0; w<tasknum;w++) {
			tasks[w] = new task();
		}
	}
}
