public class Singleton {
    private static Singleton instance = null;
    /* A private Constructor prevents any other
     * class from instantiating.*/
    private Singleton(){ }

    // update the instance
    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }

    /* Static 'instance' method */
    public static Singleton getInstance( ){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}