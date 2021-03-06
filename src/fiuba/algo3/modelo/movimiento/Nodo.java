package fiuba.algo3.modelo.movimiento;

import fiuba.algo3.modelo.Coordenada;

public class Nodo {

    protected static final int COSTOMOVIMIENTOLATERAL = 10;

    private Coordenada posicion;
    private Nodo padre;
    private int gCost;
    private int hCost;
    private boolean pasable;

    public Nodo( Coordenada posicion ) {
    	this.posicion = posicion;
    	pasable = true;
    }

    public void setPosicion ( Coordenada posicion ){
    	this.posicion = posicion;
    }
    
    public Coordenada getPosicion(){
    	return this.posicion;
    }
    
    public int getX(){
    	return this.posicion.getX();
    }
    
    public int getY(){
    	return this.posicion.getY();
    }
     
    public boolean esPasable(){
    	return this.pasable;
    }
    
    public void setPasable( boolean pasable ){
    	this.pasable = pasable;
    }
    
    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getGCost() {
        return gCost;
    }

    private void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public void setGCost( Nodo padre, int costoBase ) {
        setGCost(padre.getGCost() + costoBase);
    }

    public void setGCost( Nodo padre ) {
        setGCost(padre, COSTOMOVIMIENTOLATERAL);
    }

    public int calcularGCost( Nodo padre ) {
    	return (padre.getGCost() + COSTOMOVIMIENTOLATERAL);

    }

    public int calcularGCost( Nodo padre, int costoBase) {
        return ( padre.getGCost() + costoBase );
    }

    public int getHCost() {
        return hCost;
    }

    protected void setHCost(int hCost) {
        this.hCost = hCost;
    }

   
    public void setHCost(Nodo destino) {
        this.setHCost((Math.abs(this.getX() - destino.getX())
                + Math.abs(this.getY() - destino.getY()))
                * COSTOMOVIMIENTOLATERAL);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (this.getX() != other.getX()) {
            return false;
        }
        if (this.getY() != other.getY()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.getX();
        hash = 17 * hash + this.getY();
        return hash;
    }

}
