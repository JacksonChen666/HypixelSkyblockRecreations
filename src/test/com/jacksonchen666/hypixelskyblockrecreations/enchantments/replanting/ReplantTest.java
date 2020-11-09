package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReplantTest {
    private PlayerMock player1;

    @Before
    public void setUp() {
        ServerMock server = MockBukkit.mock();
        server.addSimpleWorld("world");
        player1 = server.addPlayer();
        MockBukkit.load(HypixelSkyblockRecreations.class);
        player1.setItemInHand(HSRCommand.getItems().get("replanting").createItem());
        player1.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS));
    }

    @Test
    public void testReplanting() {
        player1.getWorld().getBlockAt(0, 3, 0).setType(Material.FARMLAND);
        Block block = player1.getWorld().getBlockAt(0, 4, 0);
        block.setType(Material.WHEAT);
        player1.simulateBlockBreak(block);
        Assert.assertEquals(Material.WHEAT, block.getType());
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }
}