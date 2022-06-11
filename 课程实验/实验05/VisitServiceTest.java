public class VisitServiceTest {
    @Test
    public void testVist(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext
                ("applicationContext.xml");
        VisitService visitService= context.getBean(VisitService.class);
        try{
            visitService.visit("agree");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            visitService.visit("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
