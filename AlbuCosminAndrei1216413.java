// Esame di Fondamenti di Informatica 1/7/2020
// Albu Cosmin Andrei 1216413

import java.util.*;
import java.io.*;
import java.lang.*;

class EmptyStackException extends RuntimeException{}

class StringStack {
   // variabili di esemplare
   private String[] v;
   private static final int INIT_LENGTH = 1;
   private int vSize;
   
   public StringStack() 
   {
	   v = new String[INIT_LENGTH];
	   vSize = 0;
   }
   
   // restituisce il numero di elementi presenti
   public int size() 
   {
	   return vSize;
   }
  
  
   public boolean isEmpty() 
   {return (vSize==0);
   }
   private void resize()
   {
	   String[] newV = new String[v.length*2];
	   System.arraycopy(v,0,newV,0,v.length);
	   v = newV;
   }
   public void push(String x) 
   {
	   if(vSize==v.length)  //se la pila e' piena,ridimensiono
	   {
		   resize();
	   }
	   v[vSize++] = x;
   }
   
 
   public String top() 
   {
	   if(isEmpty())   //controllo che la pila non sia vuota
	   {
		   throw new EmptyStackException(); 
	   }
	   return v[vSize-1];
   }
   
   
   public String pop()
   {
	   String y = top(); //controllo che la pila non sia vuota
	   vSize--;
	   return y;
   }
private void mergeSort(int[] a)   //prestazione O(nlog(n))
{
	if(a==null) return;
	if(a.length<2) return; //caso base
	
	int mid = a.length/2;
	int[] left = new int[mid];
	int[] right = new int[a.length-mid];
	System.arraycopy(a,0,left,0,left.length);
	System.arraycopy(a,mid,right,0,right.length);
	
	mergeSort(left);
	mergeSort(right);
	
	merge(a,left,right);
}   
private void merge(int[] a,int[] b,int[] c)  //ordino in ordine DEcresente 
{
	int ia=0,ib=0,ic=0;
	
	while(ib<b.length && ic<c.length)
	{
		if(b[ib]>c[ic])  
		{
			a[ia++] = b[ib++];
		}
		else
		{
			a[ia++] = c[ic++];
		}
	}
	while(ib<b.length)
	{
		a[ia++] = b[ib++];
	}
	while(ib<b.length)
	{
		a[ia++] = c[ic++];
	}
}
   public int[] toSortedArray() 
   {
	   int[] leng_str_disor = new int[vSize]; 
	   for(int i=0;i<vSize;i++)
	   {
		   leng_str_disor[i] = v[i].length();  //array NON ORDINATO con le lunghezze delle stringhe presenti nella pila
	   }
	   mergeSort(leng_str_disor); //ordino l'array con le lunghezze delle stringhe in ordine decresente con l algoritmo mergeSort
	   return leng_str_disor;
   }
      
}

public class AlbuCosminAndrei1216413  // <--------- INSERIRE IL NOME OPPORTUNO ********
// usando la regola seguente: cognomeMatricola (es. pizzi123456)
// dove cognome e matricola sono il cognome e la matricola dello studente; 
// se il cognome contiene spazi o apostrofo,
// questi vanno ignorati; se contiene lettere accentate, usare la
// corrispondente lettera non accentata
//
{  
   
   public static int sum(StringStack s)
   {
	   if(s.isEmpty() || s==null)
	   {
		   return 0;
	   }
	   int somma = 0;
	   
	   StringStack d = s;
	   
	   for(int i = s.size()-1;i>=0;i--)
	   {
		   somma = somma + d.pop().length();
		   d.pop();
	   }
	   return somma; 
   }
	

   
   public static int sum2(StringStack s)
	{  
		if(s.size()<2 || s==null)
		{
			return 0;
		}
		int somma = 0;
		
		StringStack d = s;
	   
		for(int i = s.size()-1;i>=0;i--)
		{
		   d.pop();
		   somma = somma + d.pop().length();
		}
		return somma; 
	}
	
	
	/**** SUPERATA LA FASE DI COMPILAZIONE, ESEGUIRE IL PROGRAMMA PER FARE UN COLLAUDO ***/
   
	// NON eliminare la chiamata a test()
	// Aggiungere eventuale codice  per la parte 4 dopo la chiamata a questo metodo 
	public static void main(String[] args)
    {  
        test(); // un po' di collaudi... se da' errore, c'e' un errore!
    
       StringStack pila1 = new StringStack();
	   
	   Scanner file1 = null;
	   
	   try
	   {
		   file1 = new Scanner(new FileReader("testo.txt")); //leggo il file "testo.txt"
	   }
	   catch(FileNotFoundException e)
	   {
		   System.out.println("File non trovato. Termino.");
		   System.exit(1);
	   }
	   
	   while(file1.hasNextLine())
	   {
		   String line = file1.nextLine();
		   Scanner scanline = new Scanner(line);
		   
		   try
		   {
			   while(scanline.hasNext())
			   {
				   pila1.push(scanline.next());
			   }
		   }
		   catch(NoSuchElementException e)
		   {
			   System.out.println("Formato inserimento sbagliato");
		   }
	   }
	   
	   String contenuto_stamp = "";
	 
	   for(int i=pila1.size()-1;i>=0;i--)  //NON i++ !!!
	   {
		   {
			   String controllo = pila1.pop();
		   
				if(controllo.length()>=3)
				{
					contenuto_stamp = contenuto_stamp + controllo + " ";
				}
		   }
		   
		   
	   }
	   
	   System.out.println("Il contenuto della pila omettendo le parole di lunghezza <3 \u00e9 : ");
	   System.out.println(contenuto_stamp);
	   
    }
  
   
   // NON E' NECESSARIO ANALIZZARE IL CODICE SEGUENTE,
   // NON PUO' ESSERE MODIFICATO 
   private static int count = 0;
   private static void check(boolean condition)
   {  ++count;
      if (condition)
         System.out.println("Collaudo numero " + count + " OK");
	  else
      {  System.out.println("Collaudo numero " + count + " NON riuscito");
         System.exit(0);
      }
   }
   private static void test()
   {  StringStack s = new StringStack();
      String[] str = {"Nel", "mezzo", "del", "cammin", "di", "nostra", "vita", "mi", "ritrovai", "per", "una", "selva", "oscura", "che", "la", "diritta", "via", "era", "smarrita"}; 
       
      for (int i = 0; i < str.length; i++)
         s.push(str[i]);
      check(s.size() == str.length);
      
      for (int i = 0; i < 15; i++)
         s.pop();
     
      check(s.size() == (str.length-15)); // contiene: nel mezzo del cammin (top)
      
      s.push("di");
	  s.push("nostra");
	  s.push("vita");
      check(s.size() == 7); // contiene: nel mezzo del cammin di nostra vita (top)
	  
      int[] x = s.toSortedArray();
      
	  check(x.length == 7 && x[0] == 6 && x[1] == 6 && x[2] == 5 && x[3] == 4 && x[4] == 3 && x[5] == 3 && x[6] == 2);
     
	  check(sum(s) == 12);
      check(sum2(s) == 17);
      check((s.pop()).equals("vita"));
      check((s.pop()).equals("nostra"));
      check(s.size() == 5);
      while (!s.isEmpty())
         s.pop();
      try {
         s.top();
        
      } catch (EmptyStackException e)
      {  check(true);
	  }  
	  catch(ArrayIndexOutOfBoundsException e){
	       check(false);
	  }
      try {
         s.pop();
      } catch (EmptyStackException e)
      {  check(true);
	  }
	  catch(ArrayIndexOutOfBoundsException e){
	       check(false);
	  }
	  System.out.println("COLLAUDO RIUSCITO, ma potrebbero comunque esserci errori");
   }
   
}
