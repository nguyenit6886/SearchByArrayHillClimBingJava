/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchbyarrayhillclimbingjava;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Main {

    public static void HillClimbing(ArrayList<Point> list, String Dich) {

        Point Mo = list.get(0);
        ArrayList<String> Dong = new ArrayList();
        while (Mo != null) {
            Point S = new Point();
            S = Mo;
            Dong.add(S.getName());
            if (S.getName().equals(Dich)) {
                System.out.println(Dong);
                System.out.println("Thanh cong");
                return;
            }
            if (S.getChild() != null) {
                ArrayList<Point> L = new ArrayList();
                for (Point m : S.getChild()) {
                    for (int i = 0; i < Dong.size(); i++) {
                        if (m.getName().equals(Dong.get(i))) {
                            break;
                        } else if (m.getName().compareTo(Dong.get(i)) != 0 && i == Dong.size() - 1) {
                            L.add(m);
                        }
                    }
                }
                if (L.size() == 0) {
                    break;
                }
                int min = L.get(0).getValue();;
                int index = 0;
                for (int i = 1; i < L.size(); i++) {
                    if (L.get(i).getValue() < min) {
                        min = L.get(i).getValue();
                        index = i;
                    }
                }
                Point S1 = new Point();
                S1 = L.get(index);
                if (S1.getValue() <= S.getValue()) {
                    Mo = S1;
                } else {
                    Mo = null;
                }
            } else {
                Mo = null;
            }
        }
        System.out.println("Khong thanh cong");
    }

    public static void main(String[] args) {
        Point A = new Point("A", 14);
        Point B = new Point("B", 10);
        Point C = new Point("C", 10);
        Point D = new Point("D", 5);
        Point E = new Point("E", 5);
        Point F = new Point("F", 4);
        Point G = new Point("G", 4);
        Point H = new Point("H", 0);

        ArrayList<Point> listA = new ArrayList();
        listA.add(B);
        listA.add(C);
        listA.add(D);
        ArrayList<Point> listB = new ArrayList();
        listB.add(A);
        listB.add(F);
        //listB.add(H);        
        ArrayList<Point> listC = new ArrayList();
        listC.add(A);
        listC.add(D);
        listC.add(E);
        ArrayList<Point> listD = new ArrayList();
        listD.add(A);
        listD.add(C);
        listD.add(E);
        ArrayList<Point> listE = new ArrayList();
        listE.add(C);
        listE.add(D);
        listE.add(F);
        listE.add(G);
        ArrayList<Point> listF = new ArrayList();
        listF.add(B);
        listF.add(E);
        listF.add(G);
        ArrayList<Point> listG = new ArrayList();
        listG.add(E);
        listG.add(F);
        //listG.add(H);
        ArrayList<Point> listH = new ArrayList();
//        listH.add(B);
//        listH.add(G);

        A.setChild(listA);
        B.setChild(listB);
        C.setChild(listC);
        D.setChild(listD);
        E.setChild(listE);
        F.setChild(listF);
        G.setChild(listG);
        H.setChild(listH);

        ArrayList<Point> list = new ArrayList();
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        list.add(F);
        list.add(G);
        list.add(H);
        for (Point point : list) {
            System.out.println(point);
        }
        HillClimbing(list, "H");

//        Point A = new Point("A", 14);
//        Point B = new Point("B", 10);
//        Point C = new Point("C", 10);
//        Point D = new Point("D", 5);
//        Point E = new Point("E", 3);
//        Point F = new Point("F", 9);
//        Point G = new Point("G", 6);
//        Point H = new Point("H", 7);
//        
//        ArrayList<Point> listA = new ArrayList();
//        listA.add(B);
//        listA.add(C);
//        listA.add(D);
//        ArrayList<Point> listB = new ArrayList();
//        listB.add(A);
//        listB.add(D);
//        listB.add(G);        
//        ArrayList<Point> listC = new ArrayList();
//        listC.add(A);
//        listC.add(E);
//        ArrayList<Point> listD = new ArrayList();
//        listD.add(A);
//        listD.add(B);
//        listD.add(E);
//        listD.add(F);
//        listD.add(H);
//        ArrayList<Point> listE = new ArrayList();
//        listE.add(C);
//        listE.add(D);
//        ArrayList<Point> listF = new ArrayList();
//        listF.add(D);
//        ArrayList<Point> listG = new ArrayList();
//        listG.add(B);
//        ArrayList<Point> listH = new ArrayList();
//        listH.add(D);
//        
//        A.setChild(listA);
//        B.setChild(listB);
//        C.setChild(listC);
//        D.setChild(listD);
//        E.setChild(listE);
//        F.setChild(listF);
//        G.setChild(listG);
//        H.setChild(listH);
//
//        ArrayList<Point> list = new ArrayList();
//        list.add(A);
//        list.add(B);
//        list.add(C);
//        list.add(D);
//        list.add(E);
//        list.add(F);
//        list.add(G);
//        list.add(H);
//        for (Point point : list) {
//            System.out.println(point);
//        }
//        HillClimbing(list, "B");
    }

}
