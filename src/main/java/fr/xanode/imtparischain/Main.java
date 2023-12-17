package fr.xanode.imtparischain;

import fr.xanode.imtparischain.blockchain.Blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(1);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            if (data.equals("exit")) {
                System.out.println(blockchain.getBlockchain());
                break;
            }
            blockchain.addData(data);
        }
    }
}
