Tracy Kim
Lab 3

More description is in the code indicated by // sign.

First, created the task and resource class to represent each.
Then using scanner to put the next input to the arrays accordingly; all the tasks to tasks array and
each activity inside the tasks array inside each task, and all the resources to array("existing" array).
Then the actual FIFO allocation and banker method is coded below in the order.
The first one is FIFO, and I used while loop to go through the tasks array in order to process it until all tasks terminate.
Inside this while loop, I set the cycle so that each time (the inner loop for all tasks' activity to happen only once) completes,
I increment the cycle by one. If there is abortion or deadlock, then I indicate it at the end.
So in the inner loop, I set each if statement for initialize, request, compute, release, and terminate to calculate accordingly.
And to make sure that when terminate happens, it happens at the same cycle.
I also made sure for compute to finish all its cycle, and for release that it actually 'happens' in the cycle.
For the banker, I used the similar format across the coding, but changed few.
I made sure that all tasks and its claim satisfies for the safety check.

HOW TO RUN AND COMPILE THE PROGRAM:
Please put the input file in the argument section when you run it.
If input file is input1.txt, please insert that.
