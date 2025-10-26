
using System;
class Exercicio11
{
    static string codificarMensagemLetraP(string mensagem)
    {
        int n = mensagem.Length;
        char[] mensagemCodificada = new char[n * 2];
        for (int i = 0, j = 0; i < n; i++, j += 2)
        {
            mensagemCodificada[j] = 'p';
            mensagemCodificada[j + 1] = mensagem[i];
        }
        return new string(mensagemCodificada);
    }

    static string decodificarMensagem(string mensagem)
    {
        int n = mensagem.Length;
        char[] mensagemDecodificada = new char[n / 2];
        for (int i = 0, j = 0; j < n; i++, j += 2)
        {
            mensagemDecodificada[i] = mensagem[j + 1];
        }
        return new string(mensagemDecodificada);
    }

    static void Main()
    {
        string mensagem;
        Console.Write("Entre com a mensagem: ");
        mensagem = Console.ReadLine();
        Console.WriteLine("Mensagem codificada: " + codificarMensagemLetraP(mensagem));
        Console.WriteLine("Mensagem decodificada: " + decodificarMensagem(codificarMensagemLetraP(mensagem)));
    }
}
    