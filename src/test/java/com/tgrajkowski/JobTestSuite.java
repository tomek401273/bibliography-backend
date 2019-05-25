//package com.tgrajkowski;
//
//import com.tgrajkowski.model.job.*;
//import com.tgrajkowski.user.UserDao;
//import com.tgrajkowski.user.Users;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JobTestSuite {
//    @Autowired
//    private JobsDao jobsDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private JobDaoIml jobDao;
//
//    @Autowired
//    private JobDaoIml jobDaoIml;
//
//    @Test
//    public void insetRandomData() {
//        Users user = userDao.findById((long)1).get();
//        Job job = new Job();
//        job.setTitle("new-sotry.docx");
//        job.setDate(new Date());
//        job.setUsers(user);
//        jobsDao.save(job);
//
//    }
//
//    @Test
//    public void countByDate(){
////        List<Job> jobList =jobsDao.countByDate_Day();
////        jobList.forEach(System.out::println);
//    }
//
//    @Test
//    public void criteiaTest1() {
//
//       List<JobDaily> jobDtos = jobDaoIml.findPipelinedStatements();
//        System.out.println(jobDtos.size());
//
//        for (JobDaily jobDto : jobDtos) {
//            System.out.println(jobDto.toString());
//        }
//    }
//}
