import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:maxkey_flutter/maxkey/maxkey.dart';
import 'package:maxkey_flutter/maxkey/services/users.service.dart';
import 'package:maxkey_flutter/utils.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

part 'package:maxkey_flutter/pages/user_page/full_user_info_dialog.dart';

class UserPage extends StatelessWidget {
  const UserPage({super.key, this.user});

  final MaxKeyUser? user;

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;

    return Scaffold(
      appBar: AppBar(title: Text(AppLocalizations.of(context)!.userPageTitle)),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Column(children: [
            if (user != null)
              Ink(
                decoration: BoxDecoration(
                  color: scheme.surfaceContainer,
                  borderRadius: BorderRadius.circular(8.0),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(children: [
                    _UserCard(user: user!),
                    _UserPageButtonTile(
                      title: AppLocalizations.of(context)!.userPageUserInfoBtn,
                      trailing: Icons.info,
                      onTap: () async {
                        final userInfo = await MaxKey.instance.usersService
                            .getFullUserInfo();
                        if (userInfo == null) return;

                        if (context.mounted) {
                          showDialog(
                            context: context,
                            builder: (context) =>
                                _FullUserInfoDialog(userInfo: userInfo),
                          );
                        }
                      },
                    ),
                  ]),
                ),
              ),
            const SizedBox(height: 8.0),
            _UserPageButtonTile(
              title: AppLocalizations.of(context)!.userPageSettingsBtn,
              trailing: Icons.settings,
              onTap: () {
                context.push(RoutePath.settingsPage);
              },
            ),
            const SizedBox(height: 8.0),
            _UserPageButtonTile(
              title: AppLocalizations.of(context)!.userPageLogoutBtn,
              trailing: Icons.logout,
              onTap: () async {
                await MaxKey.instance.authnService.logout();

                if (context.mounted) {
                  context.pushReplacement(RoutePath.loginPage);
                }
              },
            )
          ]),
        ),
      ),
    );
  }
}

class _UserPageButtonTile extends StatelessWidget {
  const _UserPageButtonTile({
    super.key,
    required this.title,
    required this.trailing,
    required this.onTap,
  });

  final String title;
  final IconData trailing;
  final void Function() onTap;

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;
    return ListTile(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(8.0),
      ),
      tileColor: scheme.surfaceContainer,
      title: Text(title),
      trailing: Icon(trailing),
      onTap: onTap,
    );
  }
}

class _UserCard extends StatelessWidget {
  const _UserCard({super.key, required this.user});

  final MaxKeyUser user;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          ClipOval(
            child: user.picture == null
                ? Image.asset("assets/logo.jpg", width: 64, height: 64)
                : Image.memory(user.picture!, width: 64, height: 64),
          ),
          const SizedBox(width: 8),
          Expanded(
            child: Text(
              user.displayName,
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
            ),
          ),
        ],
      ),
    );
  }
}
