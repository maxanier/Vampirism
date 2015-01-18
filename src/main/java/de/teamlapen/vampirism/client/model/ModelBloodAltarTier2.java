package de.teamlapen.vampirism.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Moritz
 *
 */
public class ModelBloodAltarTier2 extends ModelBase {
	// fields
	ModelRenderer PillarFL;
	ModelRenderer PillarFR;
	ModelRenderer PillarBL;
	ModelRenderer PillarBR;
	ModelRenderer[][] sphere = new ModelRenderer[16][];

	public ModelBloodAltarTier2() {
		textureWidth = 64;
		textureHeight = 32;

		PillarFL = new ModelRenderer(this, 0, 0);
		PillarFL.addBox(0F, 0F, 0F, 1, 4, 1);
		PillarFL.setRotationPoint(-8F, 18F, -8F);
		PillarFL.setTextureSize(64, 32);
		PillarFL.mirror = true;
		setRotation(PillarFL, 0F, 0F, 0F);
		PillarFR = new ModelRenderer(this, 0, 0);
		PillarFR.addBox(0F, 0F, 0F, 1, 4, 1);
		PillarFR.setRotationPoint(7F, 18F, -8F);
		PillarFR.setTextureSize(64, 32);
		PillarFR.mirror = true;
		setRotation(PillarFR, 0F, 0F, 0F);
		PillarBL = new ModelRenderer(this, 0, 0);
		PillarBL.addBox(0F, 0F, 0F, 1, 4, 1);
		PillarBL.setRotationPoint(-8F, 18F, 7F);
		PillarBL.setTextureSize(64, 32);
		PillarBL.mirror = true;
		setRotation(PillarBL, 0F, 0F, 0F);
		PillarBR = new ModelRenderer(this, 0, 0);
		PillarBR.addBox(0F, 0F, 0F, 1, 4, 1);
		PillarBR.setRotationPoint(7F, 18F, 7F);
		PillarBR.setTextureSize(64, 32);
		PillarBR.mirror = true;
		setRotation(PillarBR, 0F, 0F, 0F);
		
		
		// sphere[0]
		sphere[0] = new ModelRenderer[5];
		sphere[0][0] = new ModelRenderer(this, 0, 0);
		sphere[0][0].addBox(-1F, 21F, -3F, 2, 1, 6);
		sphere[0][0].setRotationPoint(0F, 0F, 0F);
		sphere[0][0].setTextureSize(64, 32);
		sphere[0][0].mirror = true;
		setRotation(sphere[0][0], 0F, 0F, 0F);
		sphere[0][1] = new ModelRenderer(this, 0, 0);
		sphere[0][1].addBox(-2F, 21F, -2F, 1, 1, 4);
		sphere[0][1].setRotationPoint(0F, 0F, 0F);
		sphere[0][1].setTextureSize(64, 32);
		sphere[0][1].mirror = true;
		setRotation(sphere[0][1], 0F, 0F, 0F);
		sphere[0][2] = new ModelRenderer(this, 0, 0);
		sphere[0][2].addBox(-3F, 21F, -1F, 1, 1, 2);
		sphere[0][2].setRotationPoint(0F, 0F, 0F);
		sphere[0][2].setTextureSize(64, 32);
		sphere[0][2].mirror = true;
		setRotation(sphere[0][2], 0F, 0F, 0F);
		sphere[0][3] = new ModelRenderer(this, 0, 0);
		sphere[0][3].addBox(1F, 21F, -2F, 1, 1, 4);
		sphere[0][3].setRotationPoint(0F, 0F, 0F);
		sphere[0][3].setTextureSize(64, 32);
		sphere[0][3].mirror = true;
		setRotation(sphere[0][3], 0F, 0F, 0F);
		sphere[0][4] = new ModelRenderer(this, 0, 0);
		sphere[0][4].addBox(2F, 21F, -1F, 1, 1, 2);
		sphere[0][4].setRotationPoint(0F, 0F, 0F);
		sphere[0][4].setTextureSize(64, 32);
		sphere[0][4].mirror = true;
		setRotation(sphere[0][4], 0F, 0F, 0F);

		// sphere[1]
		sphere[1] = new ModelRenderer[12];
		sphere[1][0] = new ModelRenderer(this, 0, 0);
		sphere[1][0].addBox(-1F, 20F, -5F, 2, 1, 2);
		sphere[1][0].setRotationPoint(0F, 0F, 0F);
		sphere[1][0].setTextureSize(64, 32);
		sphere[1][0].mirror = true;
		setRotation(sphere[1][0], 0F, 0F, 0F);
		sphere[1][1] = new ModelRenderer(this, 0, 0);
		sphere[1][1].addBox(-3F, 20F, -4F, 2, 1, 2);
		sphere[1][1].setRotationPoint(0F, 0F, 0F);
		sphere[1][1].setTextureSize(64, 32);
		sphere[1][1].mirror = true;
		setRotation(sphere[1][1], 0F, 0F, 0F);
		sphere[1][2] = new ModelRenderer(this, 0, 0);
		sphere[1][2].addBox(-4F, 20F, -3F, 2, 1, 2);
		sphere[1][2].setRotationPoint(0F, 0F, 0F);
		sphere[1][2].setTextureSize(64, 32);
		sphere[1][2].mirror = true;
		setRotation(sphere[1][2], 0F, 0F, 0F);
		sphere[1][3] = new ModelRenderer(this, 0, 0);
		sphere[1][3].addBox(-5F, 20F, -1F, 2, 1, 2);
		sphere[1][3].setRotationPoint(0F, 0F, 0F);
		sphere[1][3].setTextureSize(64, 32);
		sphere[1][3].mirror = true;
		setRotation(sphere[1][3], 0F, 0F, 0F);
		sphere[1][4] = new ModelRenderer(this, 0, 0);
		sphere[1][4].addBox(-4F, 20F, 1F, 2, 1, 2);
		sphere[1][4].setRotationPoint(0F, 0F, 0F);
		sphere[1][4].setTextureSize(64, 32);
		sphere[1][4].mirror = true;
		setRotation(sphere[1][4], 0F, 0F, 0F);
		sphere[1][5] = new ModelRenderer(this, 0, 0);
		sphere[1][5].addBox(-3F, 20F, 2F, 2, 1, 2);
		sphere[1][5].setRotationPoint(0F, 0F, 0F);
		sphere[1][5].setTextureSize(64, 32);
		sphere[1][5].mirror = true;
		setRotation(sphere[1][5], 0F, 0F, 0F);
		sphere[1][6] = new ModelRenderer(this, 0, 0);
		sphere[1][6].addBox(-1F, 20F, 3F, 2, 1, 2);
		sphere[1][6].setRotationPoint(0F, 0F, 0F);
		sphere[1][6].setTextureSize(64, 32);
		sphere[1][6].mirror = true;
		setRotation(sphere[1][6], 0F, 0F, 0F);
		sphere[1][7] = new ModelRenderer(this, 0, 0);
		sphere[1][7].addBox(1F, 20F, 2F, 2, 1, 2);
		sphere[1][7].setRotationPoint(0F, 0F, 0F);
		sphere[1][7].setTextureSize(64, 32);
		sphere[1][7].mirror = true;
		setRotation(sphere[1][7], 0F, 0F, 0F);
		sphere[1][8] = new ModelRenderer(this, 0, 0);
		sphere[1][8].addBox(2F, 20F, 1F, 2, 1, 2);
		sphere[1][8].setRotationPoint(0F, 0F, 0F);
		sphere[1][8].setTextureSize(64, 32);
		sphere[1][8].mirror = true;
		setRotation(sphere[1][8], 0F, 0F, 0F);
		sphere[1][9] = new ModelRenderer(this, 0, 0);
		sphere[1][9].addBox(3F, 20F, -1F, 2, 1, 2);
		sphere[1][9].setRotationPoint(0F, 0F, 0F);
		sphere[1][9].setTextureSize(64, 32);
		sphere[1][9].mirror = true;
		setRotation(sphere[1][9], 0F, 0F, 0F);
		sphere[1][10] = new ModelRenderer(this, 0, 0);
		sphere[1][10].addBox(2F, 20F, -3F, 2, 1, 2);
		sphere[1][10].setRotationPoint(0F, 0F, 0F);
		sphere[1][10].setTextureSize(64, 32);
		sphere[1][10].mirror = true;
		setRotation(sphere[1][10], 0F, 0F, 0F);
		sphere[1][11] = new ModelRenderer(this, 0, 0);
		sphere[1][11].addBox(1F, 20F, -4F, 2, 1, 2);
		sphere[1][11].setRotationPoint(0F, 0F, 0F);
		sphere[1][11].setTextureSize(64, 32);
		sphere[1][11].mirror = true;
		setRotation(sphere[1][11], 0F, 0F, 0F);
		
		
		//sphere[2]
		sphere[2] = new ModelRenderer[16];
		sphere[2][0] = new ModelRenderer(this, 0, 0);
		sphere[2][0].addBox(-2F, 19F, -6F, 4, 1, 1);
		sphere[2][0].setRotationPoint(0F, 0F, 0F);
		sphere[2][0].setTextureSize(64, 32);
		sphere[2][0].mirror = true;
		setRotation(sphere[2][0], 0F, 0F, 0F);
		sphere[2][1] = new ModelRenderer(this, 0, 0);
		sphere[2][1].addBox(-4F, 19F, -5F, 3, 1, 1);
		sphere[2][1].setRotationPoint(0F, 0F, 0F);
		sphere[2][1].setTextureSize(64, 32);
		sphere[2][1].mirror = true;
		setRotation(sphere[2][1], 0F, 0F, 0F);
		sphere[2][2] = new ModelRenderer(this, 0, 0);
		sphere[2][2].addBox(-5F, 19F, -4F, 2, 1, 1);
		sphere[2][2].setRotationPoint(0F, 0F, 0F);
		sphere[2][2].setTextureSize(64, 32);
		sphere[2][2].mirror = true;
		setRotation(sphere[2][2], 0F, 0F, 0F);
		sphere[2][3] = new ModelRenderer(this, 0, 0);
		sphere[2][3].addBox(-5F, 19F, -3F, 1, 1, 2);
		sphere[2][3].setRotationPoint(0F, 0F, 0F);
		sphere[2][3].setTextureSize(64, 32);
		sphere[2][3].mirror = true;
		setRotation(sphere[2][3], 0F, 0F, 0F);
		sphere[2][4] = new ModelRenderer(this, 0, 0);
		sphere[2][4].addBox(-6F, 19F, -2F, 1, 1, 4);
		sphere[2][4].setRotationPoint(0F, 0F, 0F);
		sphere[2][4].setTextureSize(64, 32);
		sphere[2][4].mirror = true;
		setRotation(sphere[2][4], 0F, 0F, 0F);
		sphere[2][5] = new ModelRenderer(this, 0, 0);
		sphere[2][5].addBox(-5F, 19F, 1F, 1, 1, 2);
		sphere[2][5].setRotationPoint(0F, 0F, 0F);
		sphere[2][5].setTextureSize(64, 32);
		sphere[2][5].mirror = true;
		setRotation(sphere[2][5], 0F, 0F, 0F);
		sphere[2][6] = new ModelRenderer(this, 0, 0);
		sphere[2][6].addBox(-5F, 19F, 3F, 2, 1, 1);
		sphere[2][6].setRotationPoint(0F, 0F, 0F);
		sphere[2][6].setTextureSize(64, 32);
		sphere[2][6].mirror = true;
		setRotation(sphere[2][6], 0F, 0F, 0F);
		sphere[2][7] = new ModelRenderer(this, 0, 0);
		sphere[2][7].addBox(-4F, 19F, 4F, 3, 1, 1);
		sphere[2][7].setRotationPoint(0F, 0F, 0F);
		sphere[2][7].setTextureSize(64, 32);
		sphere[2][7].mirror = true;
		setRotation(sphere[2][7], 0F, 0F, 0F);
		sphere[2][8] = new ModelRenderer(this, 0, 0);
		sphere[2][8].addBox(-2F, 19F, 5F, 4, 1, 1);
		sphere[2][8].setRotationPoint(0F, 0F, 0F);
		sphere[2][8].setTextureSize(64, 32);
		sphere[2][8].mirror = true;
		setRotation(sphere[2][8], 0F, 0F, 0F);
		sphere[2][9] = new ModelRenderer(this, 0, 0);
		sphere[2][9].addBox(1F, 19F, 4F, 3, 1, 1);
		sphere[2][9].setRotationPoint(0F, 0F, 0F);
		sphere[2][9].setTextureSize(64, 32);
		sphere[2][9].mirror = true;
		setRotation(sphere[2][9], 0F, 0F, 0F);
		sphere[2][10] = new ModelRenderer(this, 0, 0);
		sphere[2][10].addBox(3F, 19F, 3F, 2, 1, 1);
		sphere[2][10].setRotationPoint(0F, 0F, 0F);
		sphere[2][10].setTextureSize(64, 32);
		sphere[2][10].mirror = true;
		setRotation(sphere[2][10], 0F, 0F, 0F);
		sphere[2][11] = new ModelRenderer(this, 0, 0);
		sphere[2][11].addBox(4F, 19F, 1F, 1, 1, 2);
		sphere[2][11].setRotationPoint(0F, 0F, 0F);
		sphere[2][11].setTextureSize(64, 32);
		sphere[2][11].mirror = true;
		setRotation(sphere[2][11], 0F, 0F, 0F);
		sphere[2][12] = new ModelRenderer(this, 0, 0);
		sphere[2][12].addBox(5F, 19F, -2F, 1, 1, 4);
		sphere[2][12].setRotationPoint(0F, 0F, 0F);
		sphere[2][12].setTextureSize(64, 32);
		sphere[2][12].mirror = true;
		setRotation(sphere[2][12], 0F, 0F, 0F);
		sphere[2][13] = new ModelRenderer(this, 0, 0);
		sphere[2][13].addBox(4F, 19F, -3F, 1, 1, 2);
		sphere[2][13].setRotationPoint(0F, 0F, 0F);
		sphere[2][13].setTextureSize(64, 32);
		sphere[2][13].mirror = true;
		setRotation(sphere[2][13], 0F, 0F, 0F);
		sphere[2][14] = new ModelRenderer(this, 0, 0);
		sphere[2][14].addBox(3F, 19F, -4F, 2, 1, 1);
		sphere[2][14].setRotationPoint(0F, 0F, 0F);
		sphere[2][14].setTextureSize(64, 32);
		sphere[2][14].mirror = true;
		setRotation(sphere[2][14], 0F, 0F, 0F);
		sphere[2][15] = new ModelRenderer(this, 0, 0);
		sphere[2][15].addBox(1F, 19F, -5F, 3, 1, 1);
		sphere[2][15].setRotationPoint(0F, 0F, 0F);
		sphere[2][15].setTextureSize(64, 32);
		sphere[2][15].mirror = true;
		setRotation(sphere[2][15], 0F, 0F, 0F);
		
		
		//sphere[3]
		sphere[3] = new ModelRenderer[16];
		sphere[3][0] = new ModelRenderer(this, 0, 0);
		sphere[3][0].addBox(-1F, 18F, -7F, 2, 1, 1);
		sphere[3][0].setRotationPoint(0F, 0F, 0F);
		sphere[3][0].setTextureSize(64, 32);
		sphere[3][0].mirror = true;
		setRotation(sphere[3][0], 0F, 0F, 0F);
		sphere[3][1] = new ModelRenderer(this, 0, 0);
		sphere[3][1].addBox(-4F, 18F, -6F, 3, 1, 1);
		sphere[3][1].setRotationPoint(0F, 0F, 0F);
		sphere[3][1].setTextureSize(64, 32);
		sphere[3][1].mirror = true;
		setRotation(sphere[3][1], 0F, 0F, 0F);
		sphere[3][2] = new ModelRenderer(this, 0, 0);
		sphere[3][2].addBox(-5F, 18F, -5F, 1, 1, 1);
		sphere[3][2].setRotationPoint(0F, 0F, 0F);
		sphere[3][2].setTextureSize(64, 32);
		sphere[3][2].mirror = true;
		setRotation(sphere[3][2], 0F, 0F, 0F);
		sphere[3][3] = new ModelRenderer(this, 0, 0);
		sphere[3][3].addBox(-6F, 18F, -4F, 1, 1, 3);
		sphere[3][3].setRotationPoint(0F, 0F, 0F);
		sphere[3][3].setTextureSize(64, 32);
		sphere[3][3].mirror = true;
		setRotation(sphere[3][3], 0F, 0F, 0F);
		sphere[3][4] = new ModelRenderer(this, 0, 0);
		sphere[3][4].addBox(-7F, 18F, -1F, 1, 1, 2);
		sphere[3][4].setRotationPoint(0F, 0F, 0F);
		sphere[3][4].setTextureSize(64, 32);
		sphere[3][4].mirror = true;
		setRotation(sphere[3][4], 0F, 0F, 0F);
		sphere[3][5] = new ModelRenderer(this, 0, 0);
		sphere[3][5].addBox(-6F, 18F, 1F, 1, 1, 3);
		sphere[3][5].setRotationPoint(0F, 0F, 0F);
		sphere[3][5].setTextureSize(64, 32);
		sphere[3][5].mirror = true;
		setRotation(sphere[3][5], 0F, 0F, 0F);
		sphere[3][6] = new ModelRenderer(this, 0, 0);
		sphere[3][6].addBox(-5F, 18F, 4F, 1, 1, 1);
		sphere[3][6].setRotationPoint(0F, 0F, 0F);
		sphere[3][6].setTextureSize(64, 32);
		sphere[3][6].mirror = true;
		setRotation(sphere[3][6], 0F, 0F, 0F);
		sphere[3][8] = new ModelRenderer(this, 0, 0);
		sphere[3][8].addBox(-1F, 18F, 6F, 2, 1, 1);
		sphere[3][8].setRotationPoint(0F, 0F, 0F);
		sphere[3][8].setTextureSize(64, 32);
		sphere[3][8].mirror = true;
		setRotation(sphere[3][8], 0F, 0F, 0F);
		sphere[3][7] = new ModelRenderer(this, 0, 0);
		sphere[3][7].addBox(-4F, 18F, 5F, 3, 1, 1);
		sphere[3][7].setRotationPoint(0F, 0F, 0F);
		sphere[3][7].setTextureSize(64, 32);
		sphere[3][7].mirror = true;
		setRotation(sphere[3][7], 0F, 0F, 0F);
		sphere[3][9] = new ModelRenderer(this, 0, 0);
		sphere[3][9].addBox(1F, 18F, 5F, 3, 1, 1);
		sphere[3][9].setRotationPoint(0F, 0F, 0F);
		sphere[3][9].setTextureSize(64, 32);
		sphere[3][9].mirror = true;
		setRotation(sphere[3][9], 0F, 0F, 0F);
		sphere[3][10] = new ModelRenderer(this, 0, 0);
		sphere[3][10].addBox(4F, 18F, 4F, 1, 1, 1);
		sphere[3][10].setRotationPoint(0F, 0F, 0F);
		sphere[3][10].setTextureSize(64, 32);
		sphere[3][10].mirror = true;
		setRotation(sphere[3][10], 0F, 0F, 0F);
		sphere[3][11] = new ModelRenderer(this, 0, 0);
		sphere[3][11].addBox(5F, 18F, 1F, 1, 1, 3);
		sphere[3][11].setRotationPoint(0F, 0F, 0F);
		sphere[3][11].setTextureSize(64, 32);
		sphere[3][11].mirror = true;
		setRotation(sphere[3][11], 0F, 0F, 0F);
		sphere[3][13] = new ModelRenderer(this, 0, 0);
		sphere[3][13].addBox(5F, 18F, -4F, 1, 1, 3);
		sphere[3][13].setRotationPoint(0F, 0F, 0F);
		sphere[3][13].setTextureSize(64, 32);
		sphere[3][13].mirror = true;
		setRotation(sphere[3][13], 0F, 0F, 0F);
		sphere[3][12] = new ModelRenderer(this, 0, 0);
		sphere[3][12].addBox(6F, 18F, -1F, 1, 1, 2);
		sphere[3][12].setRotationPoint(0F, 0F, 0F);
		sphere[3][12].setTextureSize(64, 32);
		sphere[3][12].mirror = true;
		setRotation(sphere[3][12], 0F, 0F, 0F);
		sphere[3][14] = new ModelRenderer(this, 0, 0);
		sphere[3][14].addBox(4F, 18F, -5F, 1, 1, 1);
		sphere[3][14].setRotationPoint(0F, 0F, 0F);
		sphere[3][14].setTextureSize(64, 32);
		sphere[3][14].mirror = true;
		setRotation(sphere[3][14], 0F, 0F, 0F);
		sphere[3][15] = new ModelRenderer(this, 0, 0);
		sphere[3][15].addBox(1F, 18F, -6F, 3, 1, 1);
		sphere[3][15].setRotationPoint(0F, 0F, 0F);
		sphere[3][15].setTextureSize(64, 32);
		sphere[3][15].mirror = true;
		setRotation(sphere[3][15], 0F, 0F, 0F);
		
		
		//sphere[4]
		sphere[4] = new ModelRenderer[12];
		sphere[4][0] = new ModelRenderer(this, 0, 0);
		sphere[4][0].addBox(-3F, 17F, -7F, 6, 1, 1);
		sphere[4][0].setRotationPoint(0F, 0F, 0F);
		sphere[4][0].setTextureSize(64, 32);
		sphere[4][0].mirror = true;
		setRotation(sphere[4][0], 0F, 0F, 0F);
		sphere[4][1] = new ModelRenderer(this, 0, 0);
		sphere[4][1].addBox(-5F, 17F, -6F, 2, 1, 1);
		sphere[4][1].setRotationPoint(0F, 0F, 0F);
		sphere[4][1].setTextureSize(64, 32);
		sphere[4][1].mirror = true;
		setRotation(sphere[4][1], 0F, 0F, 0F);
		sphere[4][2] = new ModelRenderer(this, 0, 0);
		sphere[4][2].addBox(-6F, 17F, -5F, 1, 1, 2);
		sphere[4][2].setRotationPoint(0F, 0F, 0F);
		sphere[4][2].setTextureSize(64, 32);
		sphere[4][2].mirror = true;
		setRotation(sphere[4][2], 0F, 0F, 0F);
		sphere[4][3] = new ModelRenderer(this, 0, 0);
		sphere[4][3].addBox(-7F, 17F, -3F, 1, 1, 6);
		sphere[4][3].setRotationPoint(0F, 0F, 0F);
		sphere[4][3].setTextureSize(64, 32);
		sphere[4][3].mirror = true;
		setRotation(sphere[4][3], 0F, 0F, 0F);
		sphere[4][4] = new ModelRenderer(this, 0, 0);
		sphere[4][4].addBox(-6F, 17F, 3F, 1, 1, 2);
		sphere[4][4].setRotationPoint(0F, 0F, 0F);
		sphere[4][4].setTextureSize(64, 32);
		sphere[4][4].mirror = true;
		setRotation(sphere[4][4], 0F, 0F, 0F);
		sphere[4][5] = new ModelRenderer(this, 0, 0);
		sphere[4][5].addBox(-5F, 17F, 5F, 2, 1, 1);
		sphere[4][5].setRotationPoint(0F, 0F, 0F);
		sphere[4][5].setTextureSize(64, 32);
		sphere[4][5].mirror = true;
		setRotation(sphere[4][5], 0F, 0F, 0F);
		sphere[4][7] = new ModelRenderer(this, 0, 0);
		sphere[4][7].addBox(3F, 17F, 5F, 2, 1, 1);
		sphere[4][7].setRotationPoint(0F, 0F, 0F);
		sphere[4][7].setTextureSize(64, 32);
		sphere[4][7].mirror = true;
		setRotation(sphere[4][7], 0F, 0F, 0F);
		sphere[4][8] = new ModelRenderer(this, 0, 0);
		sphere[4][8].addBox(5F, 17F, 3F, 1, 1, 2);
		sphere[4][8].setRotationPoint(0F, 0F, 0F);
		sphere[4][8].setTextureSize(64, 32);
		sphere[4][8].mirror = true;
		setRotation(sphere[4][8], 0F, 0F, 0F);
		sphere[4][9] = new ModelRenderer(this, 0, 0);
		sphere[4][9].addBox(6F, 17F, -3F, 1, 1, 6);
		sphere[4][9].setRotationPoint(0F, 0F, 0F);
		sphere[4][9].setTextureSize(64, 32);
		sphere[4][9].mirror = true;
		setRotation(sphere[4][9], 0F, 0F, 0F);
		sphere[4][10] = new ModelRenderer(this, 0, 0);
		sphere[4][10].addBox(5F, 17F, -5F, 1, 1, 2);
		sphere[4][10].setRotationPoint(0F, 0F, 0F);
		sphere[4][10].setTextureSize(64, 32);
		sphere[4][10].mirror = true;
		setRotation(sphere[4][10], 0F, 0F, 0F);
		sphere[4][6] = new ModelRenderer(this, 0, 0);
		sphere[4][6].addBox(-3F, 17F, 6F, 6, 1, 1);
		sphere[4][6].setRotationPoint(0F, 0F, 0F);
		sphere[4][6].setTextureSize(64, 32);
		sphere[4][6].mirror = true;
		setRotation(sphere[4][6], 0F, 0F, 0F);
		sphere[4][11] = new ModelRenderer(this, 0, 0);
		sphere[4][11].addBox(3F, 17F, -6F, 2, 1, 1);
		sphere[4][11].setRotationPoint(0F, 0F, 0F);
		sphere[4][11].setTextureSize(64, 32);
		sphere[4][11].mirror = true;
		setRotation(sphere[4][11], 0F, 0F, 0F);
		
		
		//sphere[5]
		sphere[5] = new ModelRenderer[20];
		sphere[5][0] = new ModelRenderer(this, 0, 0);
		sphere[5][0].addBox(-1F, 16F, -8F, 2, 1, 1);
		sphere[5][0].setRotationPoint(0F, 0F, 0F);
		sphere[5][0].setTextureSize(64, 32);
		sphere[5][0].mirror = true;
		setRotation(sphere[5][0], 0F, 0F, 0F);
		sphere[5][1] = new ModelRenderer(this, 0, 0);
		sphere[5][1].addBox(-4F, 16F, -7F, 3, 1, 1);
		sphere[5][1].setRotationPoint(0F, 0F, 0F);
		sphere[5][1].setTextureSize(64, 32);
		sphere[5][1].mirror = true;
		setRotation(sphere[5][1], 0F, 0F, 0F);
		sphere[5][2] = new ModelRenderer(this, 0, 0);
		sphere[5][2].addBox(-5F, 16F, -6F, 1, 1, 1);
		sphere[5][2].setRotationPoint(0F, 0F, 0F);
		sphere[5][2].setTextureSize(64, 32);
		sphere[5][2].mirror = true;
		setRotation(sphere[5][2], 0F, 0F, 0F);
		sphere[5][3] = new ModelRenderer(this, 0, 0);
		sphere[5][3].addBox(-6F, 16F, -5F, 1, 1, 1);
		sphere[5][3].setRotationPoint(0F, 0F, 0F);
		sphere[5][3].setTextureSize(64, 32);
		sphere[5][3].mirror = true;
		setRotation(sphere[5][3], 0F, 0F, 0F);
		sphere[5][4] = new ModelRenderer(this, 0, 0);
		sphere[5][4].addBox(-7F, 16F, -4F, 1, 1, 3);
		sphere[5][4].setRotationPoint(0F, 0F, 0F);
		sphere[5][4].setTextureSize(64, 32);
		sphere[5][4].mirror = true;
		setRotation(sphere[5][4], 0F, 0F, 0F);
		sphere[5][5] = new ModelRenderer(this, 0, 0);
		sphere[5][5].addBox(-8F, 16F, -1F, 1, 1, 2);
		sphere[5][5].setRotationPoint(0F, 0F, 0F);
		sphere[5][5].setTextureSize(64, 32);
		sphere[5][5].mirror = true;
		setRotation(sphere[5][5], 0F, 0F, 0F);
		sphere[5][6] = new ModelRenderer(this, 0, 0);
		sphere[5][6].addBox(-7F, 16F, 1F, 1, 1, 3);
		sphere[5][6].setRotationPoint(0F, 0F, 0F);
		sphere[5][6].setTextureSize(64, 32);
		sphere[5][6].mirror = true;
		setRotation(sphere[5][6], 0F, 0F, 0F);
		sphere[5][7] = new ModelRenderer(this, 0, 0);
		sphere[5][7].addBox(-6F, 16F, 4F, 1, 1, 1);
		sphere[5][7].setRotationPoint(0F, 0F, 0F);
		sphere[5][7].setTextureSize(64, 32);
		sphere[5][7].mirror = true;
		setRotation(sphere[5][7], 0F, 0F, 0F);
		sphere[5][8] = new ModelRenderer(this, 0, 0);
		sphere[5][8].addBox(-5F, 16F, 5F, 1, 1, 1);
		sphere[5][8].setRotationPoint(0F, 0F, 0F);
		sphere[5][8].setTextureSize(64, 32);
		sphere[5][8].mirror = true;
		setRotation(sphere[5][8], 0F, 0F, 0F);
		sphere[5][10] = new ModelRenderer(this, 0, 0);
		sphere[5][10].addBox(-1F, 16F, 7F, 2, 1, 1);
		sphere[5][10].setRotationPoint(0F, 0F, 0F);
		sphere[5][10].setTextureSize(64, 32);
		sphere[5][10].mirror = true;
		setRotation(sphere[5][10], 0F, 0F, 0F);
		sphere[5][9] = new ModelRenderer(this, 0, 0);
		sphere[5][9].addBox(-4F, 16F, 6F, 3, 1, 1);
		sphere[5][9].setRotationPoint(0F, 0F, 0F);
		sphere[5][9].setTextureSize(64, 32);
		sphere[5][9].mirror = true;
		setRotation(sphere[5][9], 0F, 0F, 0F);
		sphere[5][12] = new ModelRenderer(this, 0, 0);
		sphere[5][12].addBox(4F, 16F, 5F, 1, 1, 1);
		sphere[5][12].setRotationPoint(0F, 0F, 0F);
		sphere[5][12].setTextureSize(64, 32);
		sphere[5][12].mirror = true;
		setRotation(sphere[5][12], 0F, 0F, 0F);
		sphere[5][11] = new ModelRenderer(this, 0, 0);
		sphere[5][11].addBox(1F, 16F, 6F, 3, 1, 1);
		sphere[5][11].setRotationPoint(0F, 0F, 0F);
		sphere[5][11].setTextureSize(64, 32);
		sphere[5][11].mirror = true;
		setRotation(sphere[5][11], 0F, 0F, 0F);
		sphere[5][13] = new ModelRenderer(this, 0, 0);
		sphere[5][13].addBox(5F, 16F, 4F, 1, 1, 1);
		sphere[5][13].setRotationPoint(0F, 0F, 0F);
		sphere[5][13].setTextureSize(64, 32);
		sphere[5][13].mirror = true;
		setRotation(sphere[5][13], 0F, 0F, 0F);
		sphere[5][14] = new ModelRenderer(this, 0, 0);
		sphere[5][14].addBox(6F, 16F, 1F, 1, 1, 3);
		sphere[5][14].setRotationPoint(0F, 0F, 0F);
		sphere[5][14].setTextureSize(64, 32);
		sphere[5][14].mirror = true;
		setRotation(sphere[5][14], 0F, 0F, 0F);
		sphere[5][15] = new ModelRenderer(this, 0, 0);
		sphere[5][15].addBox(7F, 16F, -1F, 1, 1, 2);
		sphere[5][15].setRotationPoint(0F, 0F, 0F);
		sphere[5][15].setTextureSize(64, 32);
		sphere[5][15].mirror = true;
		setRotation(sphere[5][15], 0F, 0F, 0F);
		sphere[5][17] = new ModelRenderer(this, 0, 0);
		sphere[5][17].addBox(5F, 16F, -5F, 1, 1, 1);
		sphere[5][17].setRotationPoint(0F, 0F, 0F);
		sphere[5][17].setTextureSize(64, 32);
		sphere[5][17].mirror = true;
		setRotation(sphere[5][17], 0F, 0F, 0F);
		sphere[5][18] = new ModelRenderer(this, 0, 0);
		sphere[5][18].addBox(4F, 16F, -6F, 1, 1, 1);
		sphere[5][18].setRotationPoint(0F, 0F, 0F);
		sphere[5][18].setTextureSize(64, 32);
		sphere[5][18].mirror = true;
		setRotation(sphere[5][18], 0F, 0F, 0F);
		sphere[5][16] = new ModelRenderer(this, 0, 0);
		sphere[5][16].addBox(6F, 16F, -4F, 1, 1, 3);
		sphere[5][16].setRotationPoint(0F, 0F, 0F);
		sphere[5][16].setTextureSize(64, 32);
		sphere[5][16].mirror = true;
		setRotation(sphere[5][16], 0F, 0F, 0F);
		sphere[5][19] = new ModelRenderer(this, 0, 0);
		sphere[5][19].addBox(1F, 16F, -7F, 3, 1, 1);
		sphere[5][19].setRotationPoint(0F, 0F, 0F);
		sphere[5][19].setTextureSize(64, 32);
		sphere[5][19].mirror = true;
		setRotation(sphere[5][19], 0F, 0F, 0F);
		
		
		//sphere[6]
		sphere[6] = new ModelRenderer[20];
		sphere[6][0] = new ModelRenderer(this, 0, 0);
		sphere[6][0].addBox(-2F, 15F, -8F, 4, 1, 1);
		sphere[6][0].setRotationPoint(0F, 0F, 0F);
		sphere[6][0].setTextureSize(64, 32);
		sphere[6][0].mirror = true;
		setRotation(sphere[6][0], 0F, 0F, 0F);
		sphere[6][1] = new ModelRenderer(this, 0, 0);
		sphere[6][1].addBox(-4F, 15F, -7F, 2, 1, 1);
		sphere[6][1].setRotationPoint(0F, 0F, 0F);
		sphere[6][1].setTextureSize(64, 32);
		sphere[6][1].mirror = true;
		setRotation(sphere[6][1], 0F, 0F, 0F);
		sphere[6][2] = new ModelRenderer(this, 0, 0);
		sphere[6][2].addBox(-6F, 15F, -6F, 2, 1, 1);
		sphere[6][2].setRotationPoint(0F, 0F, 0F);
		sphere[6][2].setTextureSize(64, 32);
		sphere[6][2].mirror = true;
		setRotation(sphere[6][2], 0F, 0F, 0F);
		sphere[6][3] = new ModelRenderer(this, 0, 0);
		sphere[6][3].addBox(-6F, 15F, -5F, 1, 1, 1);
		sphere[6][3].setRotationPoint(0F, 0F, 0F);
		sphere[6][3].setTextureSize(64, 32);
		sphere[6][3].mirror = true;
		setRotation(sphere[6][3], 0F, 0F, 0F);
		sphere[6][4] = new ModelRenderer(this, 0, 0);
		sphere[6][4].addBox(-6F, 15F, -5F, 1, 1, 2);
		sphere[6][4].setRotationPoint(-1F, 0F, 1F);
		sphere[6][4].setTextureSize(64, 32);
		sphere[6][4].mirror = true;
		setRotation(sphere[6][4], 0F, 0F, 0F);
		sphere[6][5] = new ModelRenderer(this, 0, 0);
		sphere[6][5].addBox(-7F, 15F, -3F, 1, 1, 4);
		sphere[6][5].setRotationPoint(-1F, 0F, 1F);
		sphere[6][5].setTextureSize(64, 32);
		sphere[6][5].mirror = true;
		setRotation(sphere[6][5], 0F, 0F, 0F);
		sphere[6][6] = new ModelRenderer(this, 0, 0);
		sphere[6][6].addBox(-6F, 15F, 1F, 1, 1, 2);
		sphere[6][6].setRotationPoint(-1F, 0F, 1F);
		sphere[6][6].setTextureSize(64, 32);
		sphere[6][6].mirror = true;
		setRotation(sphere[6][6], 0F, 0F, 0F);
		sphere[6][7] = new ModelRenderer(this, 0, 0);
		sphere[6][7].addBox(-6F, 15F, 4F, 1, 1, 1);
		sphere[6][7].setRotationPoint(0F, 0F, 0F);
		sphere[6][7].setTextureSize(64, 32);
		sphere[6][7].mirror = true;
		setRotation(sphere[6][7], 0F, 0F, 0F);
		sphere[6][8] = new ModelRenderer(this, 0, 0);
		sphere[6][8].addBox(-6F, 15F, 5F, 2, 1, 1);
		sphere[6][8].setRotationPoint(0F, 0F, 0F);
		sphere[6][8].setTextureSize(64, 32);
		sphere[6][8].mirror = true;
		setRotation(sphere[6][8], 0F, 0F, 0F);
		sphere[6][9] = new ModelRenderer(this, 0, 0);
		sphere[6][9].addBox(-4F, 15F, 6F, 2, 1, 1);
		sphere[6][9].setRotationPoint(0F, 0F, 0F);
		sphere[6][9].setTextureSize(64, 32);
		sphere[6][9].mirror = true;
		setRotation(sphere[6][9], 0F, 0F, 0F);
		sphere[6][10] = new ModelRenderer(this, 0, 0);
		sphere[6][10].addBox(-2F, 15F, 7F, 4, 1, 1);
		sphere[6][10].setRotationPoint(0F, 0F, 0F);
		sphere[6][10].setTextureSize(64, 32);
		sphere[6][10].mirror = true;
		setRotation(sphere[6][10], 0F, 0F, 0F);
		sphere[6][11] = new ModelRenderer(this, 0, 0);
		sphere[6][11].addBox(2F, 15F, 6F, 2, 1, 1);
		sphere[6][11].setRotationPoint(0F, 0F, 0F);
		sphere[6][11].setTextureSize(64, 32);
		sphere[6][11].mirror = true;
		setRotation(sphere[6][11], 0F, 0F, 0F);
		sphere[6][12] = new ModelRenderer(this, 0, 0);
		sphere[6][12].addBox(4F, 15F, 5F, 2, 1, 1);
		sphere[6][12].setRotationPoint(0F, 0F, 0F);
		sphere[6][12].setTextureSize(64, 32);
		sphere[6][12].mirror = true;
		setRotation(sphere[6][12], 0F, 0F, 0F);
		sphere[6][13] = new ModelRenderer(this, 0, 0);
		sphere[6][13].addBox(5F, 15F, 4F, 1, 1, 1);
		sphere[6][13].setRotationPoint(0F, 0F, 0F);
		sphere[6][13].setTextureSize(64, 32);
		sphere[6][13].mirror = true;
		setRotation(sphere[6][13], 0F, 0F, 0F);
		sphere[6][14] = new ModelRenderer(this, 0, 0);
		sphere[6][14].addBox(7F, 15F, 1F, 1, 1, 2);
		sphere[6][14].setRotationPoint(-1F, 0F, 1F);
		sphere[6][14].setTextureSize(64, 32);
		sphere[6][14].mirror = true;
		setRotation(sphere[6][14], 0F, 0F, 0F);
		sphere[6][15] = new ModelRenderer(this, 0, 0);
		sphere[6][15].addBox(8F, 15F, -3F, 1, 1, 4);
		sphere[6][15].setRotationPoint(-1F, 0F, 1F);
		sphere[6][15].setTextureSize(64, 32);
		sphere[6][15].mirror = true;
		setRotation(sphere[6][15], 0F, 0F, 0F);
		sphere[6][16] = new ModelRenderer(this, 0, 0);
		sphere[6][16].addBox(7F, 15F, -5F, 1, 1, 2);
		sphere[6][16].setRotationPoint(-1F, 0F, 1F);
		sphere[6][16].setTextureSize(64, 32);
		sphere[6][16].mirror = true;
		setRotation(sphere[6][16], 0F, 0F, 0F);
		sphere[6][17] = new ModelRenderer(this, 0, 0);
		sphere[6][17].addBox(5F, 15F, -5F, 1, 1, 1);
		sphere[6][17].setRotationPoint(0F, 0F, 0F);
		sphere[6][17].setTextureSize(64, 32);
		sphere[6][17].mirror = true;
		setRotation(sphere[6][17], 0F, 0F, 0F);
		sphere[6][18] = new ModelRenderer(this, 0, 0);
		sphere[6][18].addBox(4F, 15F, -6F, 2, 1, 1);
		sphere[6][18].setRotationPoint(0F, 0F, 0F);
		sphere[6][18].setTextureSize(64, 32);
		sphere[6][18].mirror = true;
		setRotation(sphere[6][18], 0F, 0F, 0F);
		sphere[6][19] = new ModelRenderer(this, 0, 0);
		sphere[6][19].addBox(2F, 15F, -7F, 2, 1, 1);
		sphere[6][19].setRotationPoint(0F, 0F, 0F);
		sphere[6][19].setTextureSize(64, 32);
		sphere[6][19].mirror = true;
		setRotation(sphere[6][19], 0F, 0F, 0F);
		
		
		//sphere[7]
		sphere[7] = new ModelRenderer[16];
		sphere[7][0] = new ModelRenderer(this, 0, 0);
		sphere[7][0].addBox(-3F, 14F, -8F, 6, 1, 1);
		sphere[7][0].setRotationPoint(0F, 0F, 0F);
		sphere[7][0].setTextureSize(64, 32);
		sphere[7][0].mirror = true;
		setRotation(sphere[7][0], 0F, 0F, 0F);
		sphere[7][1] = new ModelRenderer(this, 0, 0);
		sphere[7][1].addBox(-5F, 14F, -7F, 2, 1, 1);
		sphere[7][1].setRotationPoint(0F, 0F, 0F);
		sphere[7][1].setTextureSize(64, 32);
		sphere[7][1].mirror = true;
		setRotation(sphere[7][1], 0F, 0F, 0F);
		sphere[7][2] = new ModelRenderer(this, 0, 0);
		sphere[7][2].addBox(-6F, 14F, -6F, 1, 1, 1);
		sphere[7][2].setRotationPoint(0F, 0F, 0F);
		sphere[7][2].setTextureSize(64, 32);
		sphere[7][2].mirror = true;
		setRotation(sphere[7][2], 0F, 0F, 0F);
		sphere[7][3] = new ModelRenderer(this, 0, 0);
		sphere[7][3].addBox(-7F, 14F, -5F, 1, 1, 2);
		sphere[7][3].setRotationPoint(0F, 0F, 0F);
		sphere[7][3].setTextureSize(64, 32);
		sphere[7][3].mirror = true;
		setRotation(sphere[7][3], 0F, 0F, 0F);
		sphere[7][4] = new ModelRenderer(this, 0, 0);
		sphere[7][4].addBox(-8F, 14F, -3F, 1, 1, 6);
		sphere[7][4].setRotationPoint(0F, 0F, 0F);
		sphere[7][4].setTextureSize(64, 32);
		sphere[7][4].mirror = true;
		setRotation(sphere[7][4], 0F, 0F, 0F);
		sphere[7][5] = new ModelRenderer(this, 0, 0);
		sphere[7][5].addBox(-7F, 14F, 3F, 1, 1, 2);
		sphere[7][5].setRotationPoint(0F, 0F, 0F);
		sphere[7][5].setTextureSize(64, 32);
		sphere[7][5].mirror = true;
		setRotation(sphere[7][5], 0F, 0F, 0F);
		sphere[7][6] = new ModelRenderer(this, 0, 0);
		sphere[7][6].addBox(-6F, 14F, 5F, 1, 1, 1);
		sphere[7][6].setRotationPoint(0F, 0F, 0F);
		sphere[7][6].setTextureSize(64, 32);
		sphere[7][6].mirror = true;
		setRotation(sphere[7][6], 0F, 0F, 0F);
		sphere[7][7] = new ModelRenderer(this, 0, 0);
		sphere[7][7].addBox(-5F, 14F, 6F, 2, 1, 1);
		sphere[7][7].setRotationPoint(0F, 0F, 0F);
		sphere[7][7].setTextureSize(64, 32);
		sphere[7][7].mirror = true;
		setRotation(sphere[7][7], 0F, 0F, 0F);
		sphere[7][8] = new ModelRenderer(this, 0, 0);
		sphere[7][8].addBox(-3F, 14F, 7F, 6, 1, 1);
		sphere[7][8].setRotationPoint(0F, 0F, 0F);
		sphere[7][8].setTextureSize(64, 32);
		sphere[7][8].mirror = true;
		setRotation(sphere[7][8], 0F, 0F, 0F);
		sphere[7][9] = new ModelRenderer(this, 0, 0);
		sphere[7][9].addBox(3F, 14F, 6F, 2, 1, 1);
		sphere[7][9].setRotationPoint(0F, 0F, 0F);
		sphere[7][9].setTextureSize(64, 32);
		sphere[7][9].mirror = true;
		setRotation(sphere[7][9], 0F, 0F, 0F);
		sphere[7][10] = new ModelRenderer(this, 0, 0);
		sphere[7][10].addBox(5F, 14F, 5F, 1, 1, 1);
		sphere[7][10].setRotationPoint(0F, 0F, 0F);
		sphere[7][10].setTextureSize(64, 32);
		sphere[7][10].mirror = true;
		setRotation(sphere[7][10], 0F, 0F, 0F);
		sphere[7][11] = new ModelRenderer(this, 0, 0);
		sphere[7][11].addBox(6F, 14F, 3F, 1, 1, 2);
		sphere[7][11].setRotationPoint(0F, 0F, 0F);
		sphere[7][11].setTextureSize(64, 32);
		sphere[7][11].mirror = true;
		setRotation(sphere[7][11], 0F, 0F, 0F);
		sphere[7][12] = new ModelRenderer(this, 0, 0);
		sphere[7][12].addBox(7F, 14F, -3F, 1, 1, 6);
		sphere[7][12].setRotationPoint(0F, 0F, 0F);
		sphere[7][12].setTextureSize(64, 32);
		sphere[7][12].mirror = true;
		setRotation(sphere[7][12], 0F, 0F, 0F);
		sphere[7][13] = new ModelRenderer(this, 0, 0);
		sphere[7][13].addBox(6F, 14F, -5F, 1, 1, 2);
		sphere[7][13].setRotationPoint(0F, 0F, 0F);
		sphere[7][13].setTextureSize(64, 32);
		sphere[7][13].mirror = true;
		setRotation(sphere[7][13], 0F, 0F, 0F);
		sphere[7][14] = new ModelRenderer(this, 0, 0);
		sphere[7][14].addBox(5F, 14F, -6F, 1, 1, 1);
		sphere[7][14].setRotationPoint(0F, 0F, 0F);
		sphere[7][14].setTextureSize(64, 32);
		sphere[7][14].mirror = true;
		setRotation(sphere[7][14], 0F, 0F, 0F);
		sphere[7][15] = new ModelRenderer(this, 0, 0);
		sphere[7][15].addBox(3F, 14F, -7F, 2, 1, 1);
		sphere[7][15].setRotationPoint(0F, 0F, 0F);
		sphere[7][15].setTextureSize(64, 32);
		sphere[7][15].mirror = true;
		setRotation(sphere[7][15], 0F, 0F, 0F);
		sphere[7][0] = new ModelRenderer(this, 0, 0);
		sphere[7][0].addBox(-3F, 13F, -8F, 6, 1, 1);
		sphere[7][0].setRotationPoint(0F, 0F, 0F);
		sphere[7][0].setTextureSize(64, 32);
		sphere[7][0].mirror = true;
		setRotation(sphere[7][0], 0F, 0F, 0F);
		sphere[7][1] = new ModelRenderer(this, 0, 0);
		sphere[7][1].addBox(-5F, 13F, -7F, 2, 1, 1);
		sphere[7][1].setRotationPoint(0F, 0F, 0F);
		sphere[7][1].setTextureSize(64, 32);
		sphere[7][1].mirror = true;
		setRotation(sphere[7][1], 0F, 0F, 0F);
		sphere[7][2] = new ModelRenderer(this, 0, 0);
		sphere[7][2].addBox(-6F, 13F, -6F, 1, 1, 1);
		sphere[7][2].setRotationPoint(0F, 0F, 0F);
		sphere[7][2].setTextureSize(64, 32);
		sphere[7][2].mirror = true;
		setRotation(sphere[7][2], 0F, 0F, 0F);
		sphere[7][3] = new ModelRenderer(this, 0, 0);
		sphere[7][3].addBox(-7F, 13F, -5F, 1, 1, 2);
		sphere[7][3].setRotationPoint(0F, 0F, 0F);
		sphere[7][3].setTextureSize(64, 32);
		sphere[7][3].mirror = true;
		setRotation(sphere[7][3], 0F, 0F, 0F);
		sphere[7][4] = new ModelRenderer(this, 0, 0);
		sphere[7][4].addBox(-8F, 13F, -3F, 1, 1, 6);
		sphere[7][4].setRotationPoint(0F, 0F, 0F);
		sphere[7][4].setTextureSize(64, 32);
		sphere[7][4].mirror = true;
		setRotation(sphere[7][4], 0F, 0F, 0F);
		sphere[7][5] = new ModelRenderer(this, 0, 0);
		sphere[7][5].addBox(-7F, 13F, 3F, 1, 1, 2);
		sphere[7][5].setRotationPoint(0F, 0F, 0F);
		sphere[7][5].setTextureSize(64, 32);
		sphere[7][5].mirror = true;
		setRotation(sphere[7][5], 0F, 0F, 0F);
		sphere[7][6] = new ModelRenderer(this, 0, 0);
		sphere[7][6].addBox(-6F, 13F, 5F, 1, 1, 1);
		sphere[7][6].setRotationPoint(0F, 0F, 0F);
		sphere[7][6].setTextureSize(64, 32);
		sphere[7][6].mirror = true;
		setRotation(sphere[7][6], 0F, 0F, 0F);
		sphere[7][7] = new ModelRenderer(this, 0, 0);
		sphere[7][7].addBox(-5F, 13F, 6F, 2, 1, 1);
		sphere[7][7].setRotationPoint(0F, 0F, 0F);
		sphere[7][7].setTextureSize(64, 32);
		sphere[7][7].mirror = true;
		setRotation(sphere[7][7], 0F, 0F, 0F);
		sphere[7][8] = new ModelRenderer(this, 0, 0);
		sphere[7][8].addBox(-3F, 13F, 7F, 6, 1, 1);
		sphere[7][8].setRotationPoint(0F, 0F, 0F);
		sphere[7][8].setTextureSize(64, 32);
		sphere[7][8].mirror = true;
		setRotation(sphere[7][8], 0F, 0F, 0F);
		sphere[7][9] = new ModelRenderer(this, 0, 0);
		sphere[7][9].addBox(3F, 13F, 6F, 2, 1, 1);
		sphere[7][9].setRotationPoint(0F, 0F, 0F);
		sphere[7][9].setTextureSize(64, 32);
		sphere[7][9].mirror = true;
		setRotation(sphere[7][9], 0F, 0F, 0F);
		sphere[7][10] = new ModelRenderer(this, 0, 0);
		sphere[7][10].addBox(5F, 13F, 5F, 1, 1, 1);
		sphere[7][10].setRotationPoint(0F, 0F, 0F);
		sphere[7][10].setTextureSize(64, 32);
		sphere[7][10].mirror = true;
		setRotation(sphere[7][10], 0F, 0F, 0F);
		sphere[7][11] = new ModelRenderer(this, 0, 0);
		sphere[7][11].addBox(6F, 13F, 3F, 1, 1, 2);
		sphere[7][11].setRotationPoint(0F, 0F, 0F);
		sphere[7][11].setTextureSize(64, 32);
		sphere[7][11].mirror = true;
		setRotation(sphere[7][11], 0F, 0F, 0F);
		sphere[7][12] = new ModelRenderer(this, 0, 0);
		sphere[7][12].addBox(7F, 13F, -3F, 1, 1, 6);
		sphere[7][12].setRotationPoint(0F, 0F, 0F);
		sphere[7][12].setTextureSize(64, 32);
		sphere[7][12].mirror = true;
		setRotation(sphere[7][12], 0F, 0F, 0F);
		sphere[7][13] = new ModelRenderer(this, 0, 0);
		sphere[7][13].addBox(6F, 13F, -5F, 1, 1, 2);
		sphere[7][13].setRotationPoint(0F, 0F, 0F);
		sphere[7][13].setTextureSize(64, 32);
		sphere[7][13].mirror = true;
		setRotation(sphere[7][13], 0F, 0F, 0F);
		sphere[7][14] = new ModelRenderer(this, 0, 0);
		sphere[7][14].addBox(5F, 13F, -6F, 1, 1, 1);
		sphere[7][14].setRotationPoint(0F, 0F, 0F);
		sphere[7][14].setTextureSize(64, 32);
		sphere[7][14].mirror = true;
		setRotation(sphere[7][14], 0F, 0F, 0F);
		sphere[7][15] = new ModelRenderer(this, 0, 0);
		sphere[7][15].addBox(3F, 13F, -7F, 2, 1, 1);
		sphere[7][15].setRotationPoint(0F, 0F, 0F);
		sphere[7][15].setTextureSize(64, 32);
		sphere[7][15].mirror = true;
		setRotation(sphere[7][15], 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		PillarFL.render(f5);
		PillarFR.render(f5);
		PillarBL.render(f5);
		PillarBR.render(f5);
		sphere[0][0].render(f5);
		sphere[0][1].render(f5);
		sphere[0][2].render(f5);
		sphere[0][3].render(f5);
		sphere[0][4].render(f5);
		sphere[1][0].render(f5);
		sphere[1][1].render(f5);
		sphere[1][2].render(f5);
		sphere[1][3].render(f5);
		sphere[1][4].render(f5);
		sphere[1][5].render(f5);
		sphere[1][6].render(f5);
		sphere[1][7].render(f5);
		sphere[1][8].render(f5);
		sphere[1][9].render(f5);
		sphere[1][10].render(f5);
		sphere[1][11].render(f5);
		sphere[2][0].render(f5);
		sphere[2][1].render(f5);
		sphere[2][2].render(f5);
		sphere[2][3].render(f5);
		sphere[2][4].render(f5);
		sphere[2][5].render(f5);
		sphere[2][6].render(f5);
		sphere[2][7].render(f5);
		sphere[2][8].render(f5);
		sphere[2][9].render(f5);
		sphere[2][10].render(f5);
		sphere[2][11].render(f5);
		sphere[2][12].render(f5);
		sphere[2][13].render(f5);
		sphere[2][14].render(f5);
		sphere[2][15].render(f5);
		sphere[3][0].render(f5);
		sphere[3][1].render(f5);
		sphere[3][2].render(f5);
		sphere[3][3].render(f5);
		sphere[3][4].render(f5);
		sphere[3][5].render(f5);
		sphere[3][6].render(f5);
		sphere[3][8].render(f5);
		sphere[3][7].render(f5);
		sphere[3][9].render(f5);
		sphere[3][10].render(f5);
		sphere[3][11].render(f5);
		sphere[3][13].render(f5);
		sphere[3][12].render(f5);
		sphere[3][14].render(f5);
		sphere[3][15].render(f5);
		sphere[4][0].render(f5);
		sphere[4][1].render(f5);
		sphere[4][2].render(f5);
		sphere[4][3].render(f5);
		sphere[4][4].render(f5);
		sphere[4][5].render(f5);
		sphere[4][7].render(f5);
		sphere[4][8].render(f5);
		sphere[4][9].render(f5);
		sphere[4][10].render(f5);
		sphere[4][6].render(f5);
		sphere[4][11].render(f5);
		sphere[5][0].render(f5);
		sphere[5][1].render(f5);
		sphere[5][2].render(f5);
		sphere[5][3].render(f5);
		sphere[5][4].render(f5);
		sphere[5][5].render(f5);
		sphere[5][6].render(f5);
		sphere[5][7].render(f5);
		sphere[5][8].render(f5);
		sphere[5][10].render(f5);
		sphere[5][9].render(f5);
		sphere[5][12].render(f5);
		sphere[5][11].render(f5);
		sphere[5][13].render(f5);
		sphere[5][14].render(f5);
		sphere[5][15].render(f5);
		sphere[5][17].render(f5);
		sphere[5][18].render(f5);
		sphere[5][16].render(f5);
		sphere[5][19].render(f5);
		sphere[6][0].render(f5);
		sphere[6][1].render(f5);
		sphere[6][2].render(f5);
		sphere[6][3].render(f5);
		sphere[6][4].render(f5);
		sphere[6][5].render(f5);
		sphere[6][6].render(f5);
		sphere[6][7].render(f5);
		sphere[6][8].render(f5);
		sphere[6][9].render(f5);
		sphere[6][10].render(f5);
		sphere[6][11].render(f5);
		sphere[6][12].render(f5);
		sphere[6][13].render(f5);
		sphere[6][14].render(f5);
		sphere[6][15].render(f5);
		sphere[6][16].render(f5);
		sphere[6][17].render(f5);
		sphere[6][18].render(f5);
		sphere[6][19].render(f5);
		sphere[7][0].render(f5);
		sphere[7][1].render(f5);
		sphere[7][2].render(f5);
		sphere[7][3].render(f5);
		sphere[7][4].render(f5);
		sphere[7][5].render(f5);
		sphere[7][6].render(f5);
		sphere[7][7].render(f5);
		sphere[7][8].render(f5);
		sphere[7][9].render(f5);
		sphere[7][10].render(f5);
		sphere[7][11].render(f5);
		sphere[7][12].render(f5);
		sphere[7][13].render(f5);
		sphere[7][14].render(f5);
		sphere[7][15].render(f5);
		sphere[7][0].render(f5);
		sphere[7][1].render(f5);
		sphere[7][2].render(f5);
		sphere[7][3].render(f5);
		sphere[7][4].render(f5);
		sphere[7][5].render(f5);
		sphere[7][6].render(f5);
		sphere[7][7].render(f5);
		sphere[7][8].render(f5);
		sphere[7][9].render(f5);
		sphere[7][10].render(f5);
		sphere[7][11].render(f5);
		sphere[7][12].render(f5);
		sphere[7][13].render(f5);
		sphere[7][14].render(f5);
		sphere[7][15].render(f5);
	}
	
	private void addRing(int number, int offsetUp) {
		//TODO Methods that initiate the rings that build up the model, with a parameter for the offset
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
