/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Page;

/**
 *
 * @author Lucien
 */
public class Page {
    private Number pageCourant=1;
    private int pagePrecedent;
    private int pageSuivante;
    private int nombreParPage = 5;
    private int totalPage = 0;
    private int offset;
    private String direction;
    private Number totalElement;
    
    private int page_num=1;
    private int arret_boucle=nombreParPage;

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getArret_boucle() {
        return arret_boucle;
    }

    public void setArret_boucle(int arret_boucle) {
        this.arret_boucle = arret_boucle;
    }
    
    

    public Page() {
    }

    public Number getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Number totalElement) {
        this.totalElement = totalElement;
    }
    

    public int getOffset() {
        int temp = (this.getPageCourant().intValue() - 1) * this.getNombreParPage();
        return temp;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

   

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    
    
    public void setPageCourant(String pageCurrent) {
        this.pageCourant = Integer.parseInt(pageCurrent);
    }

    public void setPagePrecedent(String pagePrecedent) {
        this.pagePrecedent = Integer.parseInt(pagePrecedent);
    }

    public void setPageSuivante(String pageSuivante) {
        this.pageSuivante = Integer.parseInt(pageSuivante);
    }

    

    public void setDirection(String direction) {
        this.direction = direction;
    }

    
    public int getPagePrecedent() {
        int temp = this.getPageCourant().intValue() - 1;
        if (temp <=0 )
            temp = 1;
        return temp;
    }

    public int getPageSuivante() {
        int temp = this.getPageCourant().intValue() + 1;
        if(temp >= this.getTotalPage())
            temp = this.getTotalPage();
        return temp;
    }

    public int getNombreParPage() {
        return nombreParPage;
    }

    public String getDirection() {
        return direction;
    }

    public Number getPageCourant() {
        return pageCourant;
    }

   
    public int getTotalPage() {
        return totalPage;
    }

    
   public int getIntervalDroite(){
       int inter = this.getPageCourant().intValue()+this.getNombreParPage();
       if (inter > this.getTotalPage())
           inter = this.getTotalPage();
       return inter;
   }
   
   public int initiale(){
       int inter = this.getPageCourant().intValue()+this.getNombreParPage();
       if (inter > this.getTotalPage())
           inter = this.getTotalPage();
       return inter;
   }
   
   public int getIntervalGauche(){
       int inter = this.getPageCourant().intValue() - this.getNombreParPage();
       if (inter < 0)
           inter = 1;
        return inter;
   }
    
   
   public int arret(){
       int temp = this.getNombreParPage()+this.getPageCourant().intValue();
       if (temp > this.getTotalPage())
           temp = this.getTotalPage();
       return temp;
   }
    
    
}
