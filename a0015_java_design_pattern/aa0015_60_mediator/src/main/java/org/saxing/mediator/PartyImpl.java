package org.saxing.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * party implementation
 *
 * @author saxing 2019/4/18 13:11
 */
public class PartyImpl implements Party {

    private final List<PartyMember> members;

    public PartyImpl() {
        this.members = new ArrayList<>();
    }

    @Override
    public void addMember(PartyMember member) {
        members.add(member);
        member.joinedParty(this);
    }

    @Override
    public void act(PartyMember actor, Action action) {
        for (PartyMember member : members) {
            if (!member.equals(actor)) {
                member.partyAction(action);
            }
        }
    }
}
