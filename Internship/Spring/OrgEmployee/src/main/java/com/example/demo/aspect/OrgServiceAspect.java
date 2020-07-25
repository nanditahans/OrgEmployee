package com.example.demo.aspect;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.OrgDTO;
import com.example.demo.entity.Org;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class OrgServiceAspect {


//    @AfterThrowing (pointcut = "execution(* com.example.demo.service.OrgService.*(..))", throwing = "ex")
//    public void logAfterThrowingAllMethods(Exception ex)
//    {
//        System.out.println("AfterThrowingAllMethods() " + ex);
//    }

    @Before("execution(* com.example.demo.service.OrgService.saveOrg(..))")
    public void SaveOrgBefore() {
        System.out.println("Org.SaveOrgbeforeAdvice() :");
    }

    @Around("execution(* com.example.demo.service.OrgService.getOrgById(..))")
    public void getOrgByIdAround(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("Org.getOrgByIdAroundBefore: " + joinPoint.getSignature().getName());
        try{
            joinPoint.proceed();
        } finally {
            System.out.println("By Around");
        }
        System.out.println("Org.getOrgByIdAroundAfter: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut="execution(* com.example.demo.service.OrgService.getOrgById(..))", returning="retVal")
    public void logAfterReturningGetEmployee(Object retVal)
    {
        System.out.println("Org.getOrgByIdAfterReturning");
        System.out.println("OrgName :  "+((Org)retVal).getOrgName());
    }
//    @Before("execution(* com.example.demo.service.OrgService.getOrgById(..))")
//    public void getOrgByIdBefore(JoinPoint joinPoint){
//        System.out.println("Org.getOrgByIdBefore: " + joinPoint.getSignature().getName());
//
//    }
    @Before("execution(* com.example.demo.service.OrgService.getAllOrg(..))")
    public void getAllOrgBefore(){
        System.out.println("Org.getAllOrgBefore: " );
    }
    @Before("execution(* com.example.demo.service.OrgService.updateOrg(..))")
    public void updateOrgBefore(){
        System.out.println("Org.updateOrgBefore: " );
    }
    @Before("execution(* com.example.demo.service.OrgService.deleteOrg(..))")
    public void deleteOrgBefore(){
        System.out.println("Org.deleteOrgBefore: " );
    }
    @After("execution(* com.example.demo.service.OrgService.saveOrg(..))")
    public void SaveOrgAfter() {
        System.out.println("Org.SaveOrgAfter : " );
    }

    @After("execution(* com.example.demo.service.OrgService.getOrgById(..))")
    public void getOrgByIdAfter(){
        System.out.println("Org.getOrgByIdAfter: " );

    }
    @After("execution(* com.example.demo.service.OrgService.getAllOrg(..))")
    public void getAllOrgAfter(){
        System.out.println("Org.getAllOrgAfter: ");
    }
    @After("execution(* com.example.demo.service.OrgService.updateOrg(..))")
    public void updateOrgAfter(){
        System.out.println("Org.updateOrgAfter: " );
    }
    @After("execution(* com.example.demo.service.OrgService.deleteOrg(..))")
    public void deleteOrgAfter(){
        System.out.println("Org.deleteOrgAfter: " );
    }
}
