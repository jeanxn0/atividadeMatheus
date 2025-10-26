
using System;
using MinhaBiblioteca;
class Program
{
    static void Main()
    {
        int n;
        Console.Write("Entre com o tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] meuVetor = new int[n];
        Biblioteca.lerVetor(meuVetor);
        Biblioteca.mostrarVetor(meuVetor);

    }
   
}