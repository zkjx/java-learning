public class VisitServiceImpl implements VisitService {
    public void visit(String str) throws Exception {
        System.out.println(str);
        if(!str.equalsIgnoreCase("agree")){
            throw new Exception("非法访问");
        }
    }
}
