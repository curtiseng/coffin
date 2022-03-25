import org.junit.jupiter.api.Test;

public class FastJson {

    @Test
    public void test() {
    }

    static Dml create() {
        Dml dml = new Dml();
        dml.setDestination("cimp");
        dml.setDatabase("cetccloud-crane");
        dml.setTable("dtu_manage");
        dml.setType("UPDATE");
        dml.setEs(1648176281000L);
        dml.setTs(1648176282498L);
        dml.setSql("");
        return dml;
    }
}
