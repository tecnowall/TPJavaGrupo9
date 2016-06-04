package fiuba.algo3.modelo;

public class Nodo {

    protected static final int COSTOMOVIMIENTOLATERAL = 10;

    private Coordenada posicion;
    private Nodo padre;
    private int gCost;
    private int hCost;

    public Nodo( Coordenada posicion ) {
    	this.posicion = posicion;
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

    private void setGCost(int gCosts) {
        this.gCost = gCosts;
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

    protected void setHCost(int hCosts) {
        this.hCost = hCosts;
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
