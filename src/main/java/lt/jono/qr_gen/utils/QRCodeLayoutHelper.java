package lt.jono.qr_gen.utils;

public class QRCodeLayoutHelper {
    public static final int[] VALUE = new int[]{10,9,8,7,6,5};

    public static int[] getRowsAndColumns(float resolution) {
        int rows;
        int columns;

        switch ((int) resolution) {
            case 70: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 70
                rows = VALUE[0]; //eilutė
                columns = VALUE[1]; //stulpelis
                break;
            case 75: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 75
                rows = VALUE[1]; //eilutė
                columns = VALUE[2]; //stulpelis
                break;
            case 80: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 80
                rows = VALUE[2];
                columns = VALUE[2];
                break;
            case 85: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 85
                rows = VALUE[2];
                columns = VALUE[3];
                break;
            case 90: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 90
                rows = VALUE[3];
                columns = VALUE[3];
                break;
            case 95: // Sutvarkyta qr kodų kiekis tinkamas rezoliucija 95
                rows = VALUE[3];
                columns = VALUE[4];
                break;
            default:
                // kai rezoliucija nepasirinkta kad negadinti lapu :) reikia sugalvoti !!
                rows = 1000;
                columns = 1000;
                break;

        }

        return new int[]{rows, columns};
    }
}
