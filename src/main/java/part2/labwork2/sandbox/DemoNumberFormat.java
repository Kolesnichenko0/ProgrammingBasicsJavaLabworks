package part2.labwork2.sandbox;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DemoNumberFormat {

    public static void main(String[] args) {
        NumberFormat nfGe = NumberFormat.getInstance(Locale.GERMAN);
        NumberFormat nfUs = NumberFormat.getInstance(Locale.US);
        NumberFormat nfFr = NumberFormat.getInstance(Locale.FRANCE);
        NumberFormat nfUa = NumberFormat.getInstance(new Locale("uk", "UA"));
        double iGe = 0, iUs = 0, iFr = 0, iUa = 0;
        String str = "4,5";
        try {
            //перетворення рядка на німецький стандарт:
            System.out.println(iGe = nfGe.parse(str).doubleValue());
            //перетворення рядка в американський стандарт:
            System.out.println(iUs = nfUs.parse(str).doubleValue());
            //перетворення рядка у французький стандарт:
            System.out.println(iFr = nfFr.parse(str).doubleValue());
            //перетворення рядка на український стандарт:
            System.out.println(iUa = nfUa.parse(str).doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println();
        String sUs = nfUs.format(iGe);//перетворення числа з німецького в американський стандарт
        String sFr = nfFr.format(iGe);//перетворення числа з німецького у французький стандарт
        String sUa = nfUa.format(iGe);//перетворення числа з німецького на український стандарт
        System.out.println(sUs + "\n" + sFr + "\n" + sUa);
    }

}

