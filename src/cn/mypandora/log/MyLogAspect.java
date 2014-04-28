///**   
// * @ProjectName: MyFramework
// * @Package: cn.mypandora.log 
// * @ClassName: MyLogAspect 
// * Copyright © hankaibo. All rights reserved.
// * @Author: kaibo
// * @CreateDate: 2014-4-27 下午3:02:55 
// *
// */
//package cn.mypandora.log;
//
//import javax.annotation.Resource;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import cn.mypandora.system.service.BaseLogService;
//
///**
// * @ClassName: MyLogAspect
// * @Description: TODO
// * @Author: kaibo
// * @date: 2014-4-27
// * @UpdateUser: kaibo
// * @UpdateDate: 2014-4-27 下午3:02:55
// * @UpdateRemark: What is modified?
// */
//@Aspect
//public class MyLogAspect {
//    private static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);
//
//    @Resource
//    private BaseLogService service;
//
//    @AfterReturning("execution(* del*(..))")
//    public void doSuccessLog(JoinPoint point){
//        //获取目标方法体参数
//        Object[] parames=point.getArgs();
////        //解析目标方法体参数
////        String params=parseParames(parames);
////        //获取目标类名
////        String className=point.getSignature().getClass().toString();
////        className=className.substring(className.indexOf("cn"));
////        //获取目标方法签名
////        String signature=point.getSignature().toString();
////        String methodName=signature.substring(signature.lastIndexOf(".")+1,signature.indexOf("("));
////        //根据类名获取所属的模块
////        String modelName="aaa";//getModelName(className);
////        BaseLog log=new BaseLog();
////          log.setCreateTime(new Timestamp(System.currentTimeMillis()));
////          log.setUsername("从Session中获取的当前用户名称");
////          log.setIp("127.0.0.1");
//////          log.setDescription(anno.description());
////    
////          service.addEntity(log);
////        point.getSignature().getClass().getMethods();
////        String methodName = point.getSignature().getName();
////        if (StringUtils.isNotEmpty(methodName)) {
////            if (!methodName.startsWith("set") || !methodName.startsWith("get") || !methodName.startsWith("is")) {
////                Class targetClass = point.getTarget().getClass();
////                Method method = targetClass.getMethod(methodName);
////
////                if (method != null) {
////                    boolean hasAnnotation = method.isAnnotationPresent(MyMethodAnno.class);
////                    if (hasAnnotation) {
////                        MyMethodAnno annotation = method.getAnnotation(MyMethodAnno.class);
////                        String methodDesc = annotation.description();
////                        logger.debug("Service method:" + method.getName() + " Description:" + methodDesc);
////
////                        BaseLog log=new BaseLog();
////                        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
////                        log.setUsername("从Session中获取的当前用户名称");
////                        log.setIp("127.0.0.1");
////                        log.setDescription(methodDesc);
////
////                        service.addEntity(log);
////                    }
////                }
////            }
////        }
////        return point.proceed();
//    }
//    private String parseParames(Object[] parames) {  
//        StringBuffer sb = new StringBuffer();  
////        for(int i=0; i<parames.length; i++){  
////            if(parames[i] instanceof Object[] || parames[i] instanceof Collection){  
////                JSONArray json = JSONArray.fromObject(parames[i]);  
////                if(i==parames.length-1){  
////                    sb.append(json.toString());  
////                }else{  
////                    sb.append(json.toString() + ",");  
////                }  
////            }else{  
////                JSONObject json = JSONObject.fromObject(parames[i]);  
////                if(i==parames.length-1){  
////                    sb.append(json.toString());  
////                }else{  
////                    sb.append(json.toString() + ",");  
////                }  
////            }  
////        }
//        String params = sb.toString();  
//        params = params.replaceAll("(\"\\w+\":\"\",)", "");  
//        params = params.replaceAll("(,\"\\w+\":\"\")", "");  
//        return params;  
//    }  
//    /** 
//     * 根据包名查询检索其所属的模块 
//     * @param packageName 包名 
//     * @return 模块名称 
//     */  
////    private String getModelName(String packageName){  
////        String modelName = "";  
////        SAXReader reader = new SAXReader();  
////        try {  
////            //读取project.xml模块信息描述xml文档  
////            File proj = ResourceUtils.getFile("classpath:project.xml");  
////            Document doc = reader.read(proj);  
////            //获取文档根节点  
////            Element root = doc.getRootElement();  
////            //查询模块名称  
////            modelName = searchModelName(root, packageName);  
////        } catch (Exception e) {  
////            e.printStackTrace();  
////        }  
////        return modelName;  
////    }  
//}
