package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ReplantTest {
    private ServerMock server;
    private PlayerMock player1;

    @Before
    public void setUp() {
        class ServerMock2 extends ServerMock {

        }
        server = MockBukkit.mock(new ServerMock2());
        server.addSimpleWorld("world");
        player1 = server.addPlayer();
        MockBukkit.load(HypixelSkyblockRecreations.class);
        player1.setItemInHand(HSRCommand.getEnchants().get("replanting").createItem());
    }

    @Test
    public void testReplanting() {
        System.out.println(player1.getInventory().getItemInMainHand());
        System.out.println(Arrays.toString(player1.getInventory().getContents()));
        player1.getWorld().getBlockAt(0, 3, 0).setType(Material.FARMLAND);
        Block block = player1.getWorld().getBlockAt(0, 4, 0);
        block.setType(Material.WHEAT);
        player1.simulateBlockBreak(block);
        server.getScheduler().performOneTick();
        Assert.assertEquals(Material.WHEAT, block.getType());
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }
}