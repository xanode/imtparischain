package fr.xanode.imtparischain.blockchain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.Security;

@Slf4j
@Getter
@RequiredArgsConstructor
@ToString
public class Block {

    @NonNull private final String data;
    @NonNull private final int threshold;
    private final String previousBlockHash;
    private boolean isBuilt = false;

    private String hash;

    private boolean isHashValid() {
        if (this.hash == null) return false;
        String builder = "0".repeat(Math.max(0, this.threshold));
        return this.hash.substring(0, this.threshold).equals(builder);
    }

    public void build() {
        if (this.isBuilt) return;
        //log.info("Building block with data: {}", this.data);
        long nonce = 0;
        Security.addProvider(new BouncyCastleProvider());
        Keccak.Digest256 digest256 = new Keccak.Digest256();
        this.hash = new String(Hex.encode(digest256.digest((data + nonce).getBytes(StandardCharsets.UTF_8))));
        while (!this.isHashValid()) {
            nonce++;
            this.hash = new String(Hex.encode(digest256.digest((data + nonce).getBytes(StandardCharsets.UTF_8))));
        }
        this.isBuilt = true;
        //log.info("Block built with nonce: {}", nonce);
    }

    public boolean isBuilt() {
        return this.isBuilt;
    }
}
