package org.saxing.mediator;

/**
 * party member
 *
 * @author saxing 2019/4/18 13:06
 */
public interface PartyMember {

    void joinedParty(Party party);

    void partyAction(Action action);

    void act(Action action);

}
