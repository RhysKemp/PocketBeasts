/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.abstracts.AbstractCardGroup;
import cis2039.pocketbeasts.interfaces.ICard;

/**
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 */
public class InPlay extends AbstractCardGroup {

    /**
     * Overloaded method to get card at given index.
     *
     * @param index The index of the card to get.
     * @return The card at the given index.
     */
    public ICard getCard(int index) {
        return cards.get(index);
    }

}
