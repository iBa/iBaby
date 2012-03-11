package com.iBaby.abilities;

/**
 * The all around "be-quicker" ability
 * @author steffengy
 *
 */
public class SprintAbility extends Ability {
	@Override
	public float getAdditionalMeleeMovementSpeed() {
		return 0.03F;
	}
	@Override
	public float getAdditionalMoveTowardsTargetSpeed() {
		return 0.06F;
	}
	@Override
	public float getAdditionalFollowBabySpeed() {
		return 0.04F;
	}
}
