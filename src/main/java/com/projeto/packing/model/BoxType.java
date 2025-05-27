package com.projeto.packing.model;

public enum BoxType {
    CAIXA1(30,40,80), CAIXA2(80,50,40), CAIXA3(50,80,60);
    public final int h,w,l;
    BoxType(int h,int w,int l){this.h=h;this.w=w;this.l=l;}
  }
  