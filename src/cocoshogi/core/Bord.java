/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoshogi.core;

/**
 * 盤を表現するクラス
 * int(32bit) * 3 で盤(bord)を表す 手番、駒種ごとに bord を持つ
 *
 * @author moremagic
 */
public class Bord {
    //ビットボード 手番 駒種 盤面
    private int[][][] bitBord;
    public static final int PLAN_SENTE = 0; //先手
    public static final int PLAN_GOTE = 1; //後手
    public static final int FU = 0; //歩
    public static final int HI = 1; //飛
    public static final int KA = 2; //角
    public static final int KY = 3; //香
    public static final int KE = 4; //桂
    public static final int GI = 5; //銀
    public static final int KI = 6; //金
    public static final int OH = 7; //玉
    public static final int FUX = 8; //歩成
    public static final int HIX = 9; //飛成
    public static final int KAX = 10; //角成
    public static final int KYX = 11; //香成
    public static final int KEX = 12; //桂成
    public static final int GIX = 13; //銀成

    public Bord() {
        //駒の初期配置
        bitBord = new int[2][14][3];
        this.bitBord[PLAN_SENTE][FU] = new int[]{0, 0, 0x7FC0000}; //先手初期位置 歩
        this.bitBord[PLAN_SENTE][HI] = new int[]{0, 0, 0x0000400}; //先手初期位置 飛
        this.bitBord[PLAN_SENTE][KA] = new int[]{0, 0, 0x0010000}; //先手初期位置 角
        this.bitBord[PLAN_SENTE][KY] = new int[]{0, 0, 0x0000101}; //先手初期位置 香
        this.bitBord[PLAN_SENTE][KE] = new int[]{0, 0, 0x0000082}; //先手初期位置 桂
        this.bitBord[PLAN_SENTE][GI] = new int[]{0, 0, 0x0000044}; //先手初期位置 銀
        this.bitBord[PLAN_SENTE][KI] = new int[]{0, 0, 0x0000028}; //先手初期位置 金
        this.bitBord[PLAN_SENTE][OH] = new int[]{0, 0, 0x0000010}; //先手初期位置 玉

        this.bitBord[PLAN_SENTE][FUX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_SENTE][HIX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_SENTE][KAX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_SENTE][KYX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_SENTE][KEX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_SENTE][GIX] = new int[]{0, 0, 0};

        this.bitBord[PLAN_GOTE][FU] = new int[]{0x00001FF, 0, 0}; //先手初期位置 歩
        this.bitBord[PLAN_GOTE][HI] = new int[]{0x0010000, 0, 0}; //先手初期位置 飛
        this.bitBord[PLAN_GOTE][KA] = new int[]{0x0000400, 0, 0}; //先手初期位置 角
        this.bitBord[PLAN_GOTE][KY] = new int[]{0x4040000, 0, 0}; //先手初期位置 香
        this.bitBord[PLAN_GOTE][KE] = new int[]{0x2080000, 0, 0}; //先手初期位置 桂
        this.bitBord[PLAN_GOTE][GI] = new int[]{0x1100000, 0, 0}; //先手初期位置 銀
        this.bitBord[PLAN_GOTE][KI] = new int[]{0x0A00000, 0, 0}; //先手初期位置 金
        this.bitBord[PLAN_GOTE][OH] = new int[]{0x0400000, 0, 0}; //先手初期位置 玉

        this.bitBord[PLAN_GOTE][FUX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_GOTE][HIX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_GOTE][KAX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_GOTE][KYX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_GOTE][KEX] = new int[]{0, 0, 0};
        this.bitBord[PLAN_GOTE][GIX] = new int[]{0, 0, 0};
    }

    /**
     * 先手bitbordデータ取得
     * @return bitbord
     */
    public int[] getBitBord_SenteAll() {
        int[] ret = new int[3];
        for (int i = 0; i < ret.length; i++) {
            for (int koma = 0; koma < 14; koma++) {
                ret[i] |= this.bitBord[PLAN_SENTE][koma][i];
            }
        }
        
        return ret;
    }
    
    /**
     * 後手bitbordデータ取得
     * @return bitbord
     */
    public int[] getBitBord_GoteAll() {
        int[] ret = new int[3];
        for (int i = 0; i < ret.length; i++) {
            for (int koma = 0; koma < 14; koma++) {
                ret[i] |= this.bitBord[PLAN_GOTE][koma][i];
            }
        }
        
        return ret;
    }
    
    /**
     * bitbordデータ取得
     * @return bitbord
     */
    public int[] getBitBord() {
        return new BitBord(getBitBord_SenteAll()).or( new BitBord(getBitBord_GoteAll()) ).toBitBordArray();
    }
    
    public String printBord() {
        StringBuilder sb = new StringBuilder();
        int lineCnt = 1;
        for (int i = 0; i < 3; i++) {
            char[] lines_all = String.format("%27s", Integer.toBinaryString(getBitBord()[i])).substring(0, 27).toCharArray();
            char[] lines_oh = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][OH][i])).substring(0, 27).toCharArray();
            char[] lines_ka = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][KA][i])).substring(0, 27).toCharArray();
            char[] lines_hi = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][HI][i])).substring(0, 27).toCharArray();
            char[] lines_ki = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][KI][i])).substring(0, 27).toCharArray();
            char[] lines_gi = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][GI][i])).substring(0, 27).toCharArray();
            char[] lines_ke = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][KE][i])).substring(0, 27).toCharArray();
            char[] lines_ky = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][KY][i])).substring(0, 27).toCharArray();
            char[] lines_fu = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_SENTE][FU][i])).substring(0, 27).toCharArray();

            char[] lines_v_oh = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][OH][i])).substring(0, 27).toCharArray();
            char[] lines_v_ka = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][KA][i])).substring(0, 27).toCharArray();
            char[] lines_v_hi = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][HI][i])).substring(0, 27).toCharArray();
            char[] lines_v_ki = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][KI][i])).substring(0, 27).toCharArray();
            char[] lines_v_gi = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][GI][i])).substring(0, 27).toCharArray();
            char[] lines_v_ke = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][KE][i])).substring(0, 27).toCharArray();
            char[] lines_v_ky = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][KY][i])).substring(0, 27).toCharArray();
            char[] lines_v_fu = String.format("%27s", Integer.toBinaryString(this.bitBord[PLAN_GOTE][FU][i])).substring(0, 27).toCharArray();

            for (int j = 0; j < 27; j++) {
                if (j % 9 == 0) {
                    if (i != 0 || j != 0) {
                        sb.append("\n");
                    }
                    sb.append("P").append(lineCnt++).append(" ");
                }

                if (lines_all[j] != '1') {
                    sb.append(" * ");
                } else if (lines_v_oh[j] == '1') {
                    sb.append("-OU");
                } else if (lines_v_ka[j] == '1') {
                    sb.append("-KA");
                } else if (lines_v_hi[j] == '1') {
                    sb.append("-HI");
                } else if (lines_v_ki[j] == '1') {
                    sb.append("-KI");
                } else if (lines_v_gi[j] == '1') {
                    sb.append("-GI");
                } else if (lines_v_ke[j] == '1') {
                    sb.append("-KE");
                } else if (lines_v_ky[j] == '1') {
                    sb.append("-KY");
                } else if (lines_v_fu[j] == '1') {
                    sb.append("-FU");
                } else if (lines_oh[j] == '1') {
                    sb.append("+OU");
                } else if (lines_ka[j] == '1') {
                    sb.append("+KA");
                } else if (lines_hi[j] == '1') {
                    sb.append("+HI");
                } else if (lines_ki[j] == '1') {
                    sb.append("+KI");
                } else if (lines_gi[j] == '1') {
                    sb.append("+GI");
                } else if (lines_ke[j] == '1') {
                    sb.append("+KE");
                } else if (lines_ky[j] == '1') {
                    sb.append("+KY");
                } else if (lines_fu[j] == '1') {
                    sb.append("+FU");
                }
            }
        }
        return sb.toString();
    }
    
    public String createAtackBord(int col, int row, int plan, int piece){
        int[][][] atackbitbordIdx = new int[2][14][];
        atackbitbordIdx[PLAN_SENTE][FU] = new int[]{2, 0, 0}; //歩
        atackbitbordIdx[PLAN_SENTE][HI] = new int[]{}; //飛
        atackbitbordIdx[PLAN_SENTE][KA] = new int[]{}; //角
        atackbitbordIdx[PLAN_SENTE][KY] = new int[]{}; //香
        atackbitbordIdx[PLAN_SENTE][KE] = new int[]{5, 0, 0, 0, 0}; //桂
        atackbitbordIdx[PLAN_SENTE][GI] = new int[]{7, 0, 5}; //銀
        atackbitbordIdx[PLAN_SENTE][KI] = new int[]{7, 5, 2}; //金
        atackbitbordIdx[PLAN_SENTE][OH] = new int[]{7, 5, 7}; //玉
        atackbitbordIdx[PLAN_SENTE][FUX] = new int[]{7, 5, 2}; //歩成;
        atackbitbordIdx[PLAN_SENTE][HIX] = new int[]{}; //飛成;
        atackbitbordIdx[PLAN_SENTE][KAX] = new int[]{}; //角成;
        atackbitbordIdx[PLAN_SENTE][KYX] = new int[]{7, 5, 2}; //香成;
        atackbitbordIdx[PLAN_SENTE][KEX] = new int[]{7, 5, 2}; //桂成;
        atackbitbordIdx[PLAN_SENTE][GIX] = new int[]{7, 5, 2}; //銀成;

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < 9 ;r++){
            String lineBuf;
            try{
                int[] atack = atackbitbordIdx[plan][piece];
                int line_num = atack[r - (row - (atack.length/2 + 1))] << 8 >> col;
                
                String buf = Integer.toBinaryString(line_num);
                if(buf.length() > 9){
                    buf = buf.substring(buf.length() - 9);
                }     
                lineBuf = String.format("%9s",buf);
            }catch(ArrayIndexOutOfBoundsException e){
                lineBuf = String.format("%9s","0");
            }
            
            sb.append(lineBuf.replaceAll(" ", "0") ).append("\n");
        }

        return sb.toString();
    }
    
    public static int getPosition(int x, int y){
        return (y != 0)?(y-1) * 9 + x: x+1;
    }

}
