/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoshogi.core;

/**
 * bitBordに対する操作の抽象化を目指します
 * @author moremagic
 */
public class BitBord {

    private int[] bitbord = new int[]{0, 0, 0};

    public BitBord(int[] bitbord) {
        this.bitbord = bitbord;
    }
    
    public int[] toBitBordArray(){
        return this.bitbord;
    }

    public BitBord or(BitBord bord) {
        int[] ret = this.bitbord;
        for (int i = 0; i < ret.length; i++) {
            ret[i] |= bord.bitbord[i];
        }
        return new BitBord(ret);
    }

    public BitBord and(BitBord bord) {
        int[] ret = this.bitbord;
        for (int i = 0; i < ret.length; i++) {
            ret[i] &= bord.bitbord[i];
        }
        return new BitBord(ret);
    }

    public String printBord() {
        StringBuilder sb = new StringBuilder();

        int lineCnt = 1;
        for (int i = 0; i < 3; i++) {
            char[] lines = String.format("%27s", Integer.toBinaryString(bitbord[i])).substring(0, 27).toCharArray();
            for (int j = 0; j < 27; j++) {
                if (j % 9 == 0) {
                    if (i != 0 || j != 0) {
                        sb.append("\n");
                    }
                    sb.append("P").append(lineCnt++).append(" ");
                }

                if (lines[j] != '1') {
                    sb.append(" 0 ");
                } else {
                    sb.append(" 1 ");
                }
            }
        }
        return sb.toString();
    }
}
