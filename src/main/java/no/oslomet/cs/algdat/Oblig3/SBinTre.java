package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator


    //Egendefinerte hjelpevariabler
    private int antallAvVerdi = 0; //Brukes i oppgave 2
    private static Node firstPost;
    private StringBuilder strRec;

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        //Kode fra programkode 5.2.3a
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;
            if(b != null) b.forelder = q;
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        int antallFjernet = 0;
        while(true){
            boolean finnes = fjern(verdi);
            if(!finnes) break;
            else antallFjernet++;
        }
        return antallFjernet;
    }

    public int antall(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        antallAvVerdi = 0;
        return antall(rot, verdi);
    }

    private int antall(Node node, T verdi){
        if(node == null) return antallAvVerdi;
        if(node.verdi == verdi) antallAvVerdi++;
        antall(node.venstre, verdi);
        antall(node.høyre, verdi);
        return antallAvVerdi;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        if(p == null) return null;

        while(true){
            //Første node i post-orden vil være den første som ikke har noen barn (der både venstre og høyre er null
            if(p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else{
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        if(p == null) return null;

        Node forelderNode = p.forelder;
        if(forelderNode == null) return null;
        //Hvis høyre barnet til forelderen er enten null eller noden p, betyr det at noden som kommer etter i post-orden er forelderen.
        if(forelderNode.høyre == null || forelderNode.høyre == p) return forelderNode;

        Node tmp = forelderNode.høyre;
        return førstePostorden(tmp);
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
        /*
        StringBuilder str = new StringBuilder();
        Node tmp = rot;

        while(tmp != null){
            str.append(" " + tmp.verdi);
            tmp = nestePostorden(tmp);
        }

        */
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        ArrayList<T> output = new ArrayList<T>();
        Deque<Node> deque = new LinkedList<Node>();
        deque.add(rot);
        while(!deque.isEmpty()){
            Node tmp = deque.poll();
            output.add((T) tmp.verdi);
            if(tmp.venstre != null) deque.add(tmp.venstre);
            if(tmp.høyre != null) deque.add(tmp.høyre);
        }
        return output;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        SBinTre<Integer> output = new SBinTre<>(Comparator.naturalOrder());

        for(K a : data){
            output.leggInn((Integer) a);
        }

        return (SBinTre<K>) output;
    }


} // ObligSBinTre
