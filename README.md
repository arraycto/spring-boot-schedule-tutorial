# Spring Boot（十五）— 任务调度

任务调度（也可以称为定时任务）是指在特定的时间段去执行一个规定的任务过程。Spring Boot为开发者提供了一个更优雅的方式创建任务调度程序。在本章节中，我们将学习使用Spring Boot来创建任务调度程序。

任务调度分为两种类型，一种是间隔时间执行的任务，如每隔3秒执行一次任务程序；另外一种时指定具体时间的任务，如在每天的凌晨整点备份数据库。



## Cron表达式

在开始讲解定时任务之前，先来看一下定时任务中的Cron表达式的相关内容。Cron表达式用于配置CronTrigger实例，它是**org.quartz.Trigger**的子类。Cron表达式被放置在**@Scheduled** 注释标签中，下面的代码给出了一个cron表达式的样例：

```java
@Scheduled(cron = "0/5 * 22 * * ?")
public void cronJobSchedule(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    logger.info("Java cron job expression scheduler::"+sdf.format(now));
}
```

在cron表达式中，一共有七位表达式参数，我们将使用一张表格来了解各个参数的用途：

| 位数   | 说明               | 范围                |
| ------ | ------------------ | ------------------- |
| 第一位 | 表示秒             | 取值范围：0-59      |
| 第二位 | 表示分钟           | 取值范围：0-59      |
| 第三位 | 表示小时           | 取值范围：0-23      |
| 第四位 | 表示日期           | 取值范围：1-31      |
| 第五位 | 表示月份           | 取值范围：1-12      |
| 第六位 | 表示星期           | 取值范围：1-7       |
| 第七位 | 表示年份，通常置空 | 取值范围：1970-2099 |

> 说明，在第六位星期参数中，1表示的是星期日，除使用数字表示外，还可以使用表示星期的英文缩写来设置

了解了cron表达式的语法规则后，我们再来了解一下表达式中各种占位符的含义。cron表达式中一共可以使用的占位符有5个，如下表所示：

| 占位符     | 说明                           | 示例                                               |
| ---------- | ------------------------------ | -------------------------------------------------- |
| （星号）*  | 可以理解为一个周期             | 每秒、没分、每小时等                               |
| （问好）？ | 只能出现在日期和星期两个位置中 | 表示时间不确定                                     |
| （横线）-  | 表示一个时间范围               | 如在小时中10-11，表示从上午10点到上午11点          |
| （逗号）， | 表示一个列表值                 | 如在星期中使用：1,3,5 表示星期一、星期三和星期五   |
| （斜杠）/  | 表示一个开始时间和间隔时间周期 | 在分钟中使用：0/15 表示从0分开始，每15分钟运行一次 |

下面将列举一些示例来说明cron表达式和占位符：

| 表达式          | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| 0 0 0 * * ？    | 每天00:00:00执行任务                                         |
| 0 30 10 * * ？  | 每天上午10:30:00执行任务                                     |
| 0 30 10 ？ * *  | 每天上午10:30:00执行任务                                     |
| 0 0/15 10 * * ? | 每天上午10:00:00、10:15:00、10:30:00和10:45:00这四个时间点执行任务 |
| 0 0 0 ? * 1     | 每个星期天的凌晨整点执行任务                                 |
| 0 0 0 ？ * 1#3  | 每个月的第三个星期天的凌晨整点执行任务                       |

你可以访问[RT社圈](https://www.ramostear.com)阅读关于Spring Boot更多的教程信息。你可以访问[https://www.ramostear.com/archive/spring-boot/post/schedule.html](https://www.ramostear.com/archive/spring-boot/post/schedule.html)查看原文信息。