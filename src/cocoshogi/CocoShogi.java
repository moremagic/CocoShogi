/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoshogi;

import cocoshogi.core.Bord;

/**
 *
 * @author moremagic
 */
public class CocoShogi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println(new Bord().printBord());
//        
//        Bord bb = new Bord();
//        for(int r = 1 ; r <= 9 ; r++){
//            for(int c = 1 ; c <= 9 ; c++){
//                System.out.println("--< " + r + ", " + c + " >--");
//                System.out.println(bb.createAtackBord( c, r, Bord.PLAN_SENTE, Bord.KY ));
//            }
//        }
        
        System.out.println( Bord.countNtz(4521344) );
        System.out.println( Bord.countNlz(4521344) );
        
    }
}
