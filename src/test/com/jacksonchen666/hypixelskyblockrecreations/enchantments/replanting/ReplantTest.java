package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReplantTest {
    private ServerMock server;
    private PlayerMock player1;

    @Before
    public void setUp() {
        server = MockBukkit.mock();
        server.addSimpleWorld("world");
        player1 = server.addPlayer();
        MockBukkit.load(HypixelSkyblockRecreations.class);
        server.execute("hsr", player1, "giveItem", "replanting");
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void onBlockBreakEvent() {
        player1.getWorld().getBlockAt(0, 3, 0).setType(Material.FARMLAND);
        Block block = player1.getWorld().getBlockAt(0, 4, 0);
        block.setType(Material.WHEAT);
        player1.simulateBlockBreak(block);
        server.getScheduler().performOneTick();
        Assert.assertEquals(Material.WHEAT, block.getType());
    }
}