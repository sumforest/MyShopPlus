import request from '@/utils/request'

/**
 * 获取用户信息
 * @param username
 * @returns {AxiosPromise}
 */
export function getProfile(username) {
    return request({
      url: 'profile/info/' + username,
      method: 'get',
    })
}

/**
 * 获取用户信息
 * @param data
 * @returns {AxiosPromise}
 */
export function updateProfile(data) {
  return request({
    url: 'profile/update',
    method: 'post',
    data
  })
}

/**
 * 更新头像
 * @param data
 */
export function modifyIcon(data) {
  return request({
    url: 'profile/modify/icon',
    method: 'post',
    data
  })
}
