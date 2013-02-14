package afm.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.core.Properties;

public class BlockLaser extends BlockAFM {

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y,
			int z, Entity entity) {
		if(entity instanceof EntityPlayerMP){
			((EntityPlayerMP) entity).sendChatToPlayer("You touched it!");
		}
		AFM.proxy.writeChatMessageToPlayer("Entity: " + entity.getEntityName());
	}

	public BlockLaser() {
		super(Properties.Block.ID_LASER, Properties.Block.TEXTUREINDEX_LASER);
	}

}
