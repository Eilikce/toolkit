package com.eilikce.toolkit;

import org.junit.Test;

public class EilikceToolKitTest {

    private static String[] OPTIONS;

    @Test
    public void startPai() {

        String[] args = new String[]{"", "", ""};
        args[0] = "pai";
        args[1] = "192.168.95.121:9200";

        EilikceToolKit.main(args);

    }

    @Test
    public void startPaiLocal() {

        String[] args = new String[]{"", "", ""};
        args[0] = "pai";
        args[1] = "192.168.95.121:9200";
        args[2] = "bakData_1548929632547.json";

        EilikceToolKit.main(args);

    }

    @Test
    public void startCai() {

        String[] args = new String[]{"", "", ""};
        args[0] = "cai";
        args[1] = "192.168.95.121:9200";

        EilikceToolKit.main(args);

    }


}
