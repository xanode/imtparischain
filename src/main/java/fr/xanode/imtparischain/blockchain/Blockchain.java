package fr.xanode.imtparischain.blockchain;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
public class Blockchain {

    private final ArrayList<Block> blockchain = new ArrayList<>();
    private final int threshold;

    public Blockchain(int threshold) {
        this.threshold = threshold;
        log.info("Creating blockchain with threshold: {}", this.threshold);
        Block genesisBlock = new Block("Genesis block", this.threshold, null);
        log.info("Initializing blockchain with genesis block...");
        genesisBlock.build();
        this.blockchain.add(genesisBlock);
        log.info("Blockchain ready!");
    }

    public void addBlock(@NonNull Block block) {
        if (!Objects.equals(block.getPreviousBlockHash(), this.blockchain.get(this.blockchain.size() - 1).getHash()))
            throw new IllegalArgumentException("Invalid previous block hash");

        if (!block.isBuilt()) block.build();
        this.blockchain.add(block);
    }

    public void addData(@NonNull String data) {
        Block block = new Block(data, this.threshold, this.blockchain.get(this.blockchain.size() - 1).getHash());
        this.addBlock(block);
    }

    public Block getBlock(int index) {
        return this.blockchain.get(index);
    }

    public String getBlockData(int index) {
        return this.blockchain.get(index).getData();
    }

    public Block getLastBlock() {
        return this.blockchain.get(this.blockchain.size() - 1);
    }

    public String getBlockchain() {
        StringBuilder builder = new StringBuilder();
        for (Block block : this.blockchain) {
            builder.append(block.toString()).append("\n");
        }
        return builder.toString();
    }
}
