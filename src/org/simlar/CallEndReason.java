/**
 * Copyright (C) 2013 - 2014 The Simlar Authors.
 *
 * This file is part of Simlar. (http://www.simlar.org)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.simlar;

public enum CallEndReason {
	NONE,
	DECLINED,
	OFFLINE,
	UNSUPPORTED_MEDIA,
	BUSY;

	public static CallEndReason fromMessage(final String message)
	{
		if (Util.isNullOrEmpty(message)) {
			return NONE;
		}

		// see linphone-android/submodules/belle-sip/src/message.c: well_known_codes
		if (message.equals("Call declined.")) {
			return DECLINED;
		} else if (message.equals("Not Found")) {
			return OFFLINE;
		} else if (message.equals("Unsupported media type")) {
			return UNSUPPORTED_MEDIA;
		} else if (message.equals("Busy here")) {
			return BUSY;
		}

		return NONE;
	}

	public int getDisplayMessageId()
	{
		switch (this) {
		case DECLINED:
			return R.string.call_activity_call_ended_because_declined;
		case OFFLINE:
			return R.string.call_activity_call_ended_because_user_offline;
		case UNSUPPORTED_MEDIA:
			return R.string.call_activity_call_ended_because_incompatible_media;
		case BUSY:
			return R.string.call_activity_call_ended_because_user_busy;
		case NONE:
		default:
			return R.string.call_activity_call_ended_normally;
		}
	}
}
