/*
 * Copyright (C) 2013 The Android Open Kang Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.about.octos.aboutoctos;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment {

    public AboutFragment() {
        // empty fragment constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about_octos, container, false);

        // hide the device maintainer information if none is provided
        String mDevDevice = getString(R.string.dev_device);
        if (mDevDevice.equals("novalue")) {
            View MaintainerLabel = root.findViewById(R.id.MaintainerLabel);
            MaintainerLabel.setVisibility(View.GONE);
            View MaintainerCard = root.findViewById(R.id.MaintainerCard);
            MaintainerCard.setVisibility(View.GONE);
        }

        // hide the XDA support thread link if none is provided
        String mSupportURL = getString(R.string.url_support_thread);
        if (mSupportURL.equals("novalue")) {
            View supportLabel = root.findViewById(R.id.device_support);
            supportLabel.setVisibility(View.GONE);
        }

        return root;
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent website = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(website);
    }

    private void launchActivity(String packageName, String activity)
            throws ActivityNotFoundException {
        Intent launch = new Intent();
        launch.setComponent(new ComponentName(packageName, packageName + activity));
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(launch);
    }

}
